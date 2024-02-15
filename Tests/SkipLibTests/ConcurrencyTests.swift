// Copyright 2023 Skip
//
// This is free software: you can redistribute and/or modify it
// under the terms of the GNU Lesser General Public License 3.0
// as published by the Free Software Foundation https://fsf.org
#if !SKIP
import Foundation
#endif
import XCTest

@available(macOS 13, macCatalyst 16, iOS 16, tvOS 16, watchOS 8, *)
final class ConcurrencyTests: XCTestCase {
    func testSimpleValue() async throws {
        let task1 = Task {
            return await asyncInt()
        }
        let task2 = Task.detached {
            return await self.asyncInt2()
        }
        let value1 = await task1.value
        let value2 = await task2.value
        XCTAssertEqual(value1, 100)
        XCTAssertEqual(value2, 200)

        let value3 = await asyncInt()
        XCTAssertEqual(value3, 100)
    }

    func testThrowsException() async throws {
        let task = Task {
            let _ = await asyncInt()
            throw ConcurrencyTestsError()
        }
        do {
            let _ = try await task.value
            XCTFail("Should have thrown ConcurrencyTestsError")
        } catch {
            XCTAssertTrue(error is ConcurrencyTestsError)
        }
    }

    func testTaskCancelWithException() async throws {
        let task = Task {
            try await Task.sleep(nanoseconds: 100_000_000)
        }
        try await Task.sleep(nanoseconds: 1_000_000)
        task.cancel()
        do {
            let _ = try await task.value
            XCTFail("Expected cancellation error")
        } catch {
            // this has been seen to fail when running against the emulator on CI:
            // skip.lib.ConcurrencyTests > runtestTaskCancelWithException$SkipLib_debugAndroidTest[Pixel_3a_API_30(AVD) - 11] FAILED
            XCTAssertTrue(error is CancellationError, "expected CancellationError but got: \(error)")
        }
    }

    func testTaskCancelWithoutException() async throws {
        let task = Task {
            var i = 0
            while i < 10_000 {
                guard !Task.isCancelled else { break }
                let _ = await asyncInt() + asyncInt2()
                i += 1
            }
            return i
        }
        let aint = await asyncInt()
        XCTAssertEqual(aint, 100)
        task.cancel()
        let iterations = await task.value
        XCTAssertLessThan(iterations, 10_000)
    }

    static var taskIsCancelled = false

    func testTaskIsCancelled() async throws {
        Self.taskIsCancelled = false
        let task = Task {
            defer { Self.taskIsCancelled = Task.isCancelled }
            try await Task.sleep(nanoseconds: 10_000_000)
        }
        let _ = await asyncInt() + asyncInt2()
        task.cancel()
        do {
            let _ = try await task.value
            XCTFail("Expected cancellation error")
        } catch {
            XCTAssertTrue(Self.taskIsCancelled)
        }

        let task2 = Task {
            return Task.isCancelled
        }
        let task2Cancelled = await task2.value
        XCTAssertFalse(task2Cancelled)
    }

    func testTaskGroup() async throws {
        let results = try await withThrowingTaskGroup(of: Int.self) { group in
            group.addTask {
                return try await self.delayedInt(millis: 200)
            }
            group.addTask {
                return try await self.delayedInt(millis: 100)
            }
            group.addTask {
                return try await self.delayedInt(millis: 400)
            }
            var results: [Int] = []
            for try await result in group {
                results.append(result)
            }
            return results
        }
        XCTAssertEqual(results, [100, 200, 400])
    }

    func testThrowingTaskUncaught() async throws {
        do {
            let _ = try await withThrowingTaskGroup(of: Int.self) { group in
                group.addTask {
                    let _ = try await self.delayedInt(millis: 200)
                    throw ConcurrencyTestsError()
                }
                group.addTask {
                    return try await self.delayedInt(millis: 100)
                }
                group.addTask {
                    return try await self.delayedInt(millis: 400)
                }
                var results: [Int] = []
                for try await result in group {
                    results.append(result)
                }
                XCTFail()
                return results
            }
        } catch {
            XCTAssertTrue(error is ConcurrencyTestsError)
        }
    }

    func testThrowingTaskGroupCaught() async throws {
        var caught: Error? = nil
        var lastResult: Int? = nil
        let results = try await withThrowingTaskGroup(of: Int.self) { group in
            group.addTask {
                let _ = try await self.delayedInt(millis: 200)
                throw ConcurrencyTestsError()
            }
            group.addTask {
                return try await self.delayedInt(millis: 100)
            }
            group.addTask {
                return try await self.delayedInt(millis: 400)
            }
            var results: [Int] = []
            do {
                for try await result in group {
                    results.append(result)
                }
            } catch {
                caught = error
            }
            lastResult = try await group.next()
            return results
        }
        XCTAssertTrue(caught is ConcurrencyTestsError)
        XCTAssertEqual(results, [100])
        XCTAssertEqual(lastResult, 400)
    }

    func testTaskGroupCancel() async throws {
        let result = try await withThrowingTaskGroup(of: Int.self) { group in
            group.addTask {
                let _ = try await self.delayedInt(millis: 200)
                throw ConcurrencyTestsError()
            }
            group.addTask {
                return try await self.delayedInt(millis: 100)
            }
            group.addTask {
                return try await self.delayedInt(millis: 400)
            }
            let result = try await group.next()
            XCTAssertEqual(100, result)
            group.cancelAll()
            XCTAssertTrue(group.isCancelled)
            do {
                let _ = try await group.next()
                XCTFail()
            } catch is CancellationError {
            }
            return result ?? 0
        }
        XCTAssertEqual(result, 100)
    }

    func testAsyncLet() async throws {
        let start = currentTimeMillis()
        async let i1 = delayedInt(millis: 500)
        async let i2 = delayedInt(millis: 200)
        let sum = try await i1 + i2
        let end = currentTimeMillis()
        XCTAssertEqual(700, sum)
        // note that the timing assertion has been observed to fail under high load on the Android emulator; this is not unexpected
        XCTAssertLessThan(end - start, 700) 
    }

    func testAsyncSequence() async throws {
        let seq = AsyncIntSequence()
        var collected: [Int] = []
        for await i in seq {
            collected.append(i)
        }
        XCTAssertEqual(collected, [0, 100, 200])

        collected.removeAll()
        let mapped = seq.map { $0 * -1 }
        for await i in mapped {
            collected.append(i)
        }
        XCTAssertEqual(collected, [0, -100, -200])

        collected.removeAll()
        let compactMapped = seq.compactMap { $0 == 100 ? nil : $0 }
        for await i in compactMapped {
            collected.append(i)
        }
        XCTAssertEqual(collected, [0, 200])

        collected.removeAll()
        let flatMapped = seq.flatMap { _ in AsyncIntSequence() }
        for await i in flatMapped {
            collected.append(i)
        }
        XCTAssertEqual(collected, [0, 100, 200, 0, 100, 200, 0, 100, 200])

        collected.removeAll()
        let filtered = seq.filter { $0 == 100 }
        for await i in filtered {
            collected.append(i)
        }
        XCTAssertEqual(collected, [100])

        let first = await seq.first { $0 == 100 }
        XCTAssertEqual(first, 100)

        collected.removeAll()
        let dropped = seq.dropFirst()
        for await i in dropped {
            collected.append(i)
        }
        XCTAssertEqual(collected, [100, 200])

        collected.removeAll()
        let droppedAll = seq.dropFirst(10)
        for await i in droppedAll {
            collected.append(i)
        }
        XCTAssertEqual(collected, Array<Int>())

        collected.removeAll()
        let dropped2 = seq.drop { $0 != 100 }
        for await i in dropped2 {
            collected.append(i)
        }
        XCTAssertEqual(collected, [100, 200])

        collected.removeAll()
        let prefix = seq.prefix(2)
        for await i in prefix {
            collected.append(i)
        }
        XCTAssertEqual(collected, [0, 100])

        collected.removeAll()
        let prefixAll = seq.prefix(10)
        for await i in prefixAll {
            collected.append(i)
        }
        XCTAssertEqual(collected, [0, 100, 200])

        let min = await seq.min()
        XCTAssertEqual(min, 0)
        let min2 = await seq.min(by: >)
        XCTAssertEqual(min2, 200)

        let max = await seq.max()
        XCTAssertEqual(max, 200)
        let max2 = await seq.max(by: >)
        XCTAssertEqual(max2, 0)

        let contains = await seq.contains(100)
        XCTAssertTrue(contains)
        let contains2 = await seq.contains(300)
        XCTAssertFalse(contains2)
        let contains3 = await seq.contains { $0 == 100 }
        XCTAssertTrue(contains3)
        let contains4 = await seq.contains { $0 == 300 }
        XCTAssertFalse(contains4)

        let allSatisfy = await seq.allSatisfy { $0 >= 0 }
        XCTAssertTrue(allSatisfy)
        let allSatisfy2 = await seq.allSatisfy { $0 < 200 }
        XCTAssertFalse(allSatisfy2)

        let reduce = await seq.reduce(0, +)
        XCTAssertEqual(reduce, 300)
        var result = 0
        let reduce2 = await seq.reduce(into: result) { $0 += $1 }
        XCTAssertEqual(reduce2, 300)
    }

    func testMainActor() async throws {
        mainActorCount = 0
        let task1 = Task.detached {
            var numbers: Set<Int> = []
            for _ in 0..<100 {
                await numbers.insert(self.mainActorIncrement())
            }
            return numbers
        }
        let task2 = Task.detached {
            var numbers: Set<Int> = []
            for _ in 0..<100 {
                await numbers.insert(self.mainActorIncrement())
            }
            return numbers
        }
        let task3 = Task.detached {
            var numbers: Set<Int> = []
            for _ in 0..<100 {
                await numbers.insert(self.mainActorIncrement())
            }
            return numbers
        }
        let set1 = await task1.value
        let set2 = await task2.value
        let set3 = await task3.value

        // @MainActor should have forced exclusive access and prevented races
        let combined = set1.union(set2).union(set3)
        XCTAssertEqual(300, combined.count)
    }

    var mainActorCount = 0

    @MainActor func mainActorIncrement() -> Int {
        mainActorCount += 1
        return mainActorCount
    }

    func currentTimeMillis() -> Int {
        // We're below the level of SkipFoundation, so we don't have access to the transpiled Date API
        #if SKIP
        return Int(java.lang.System.currentTimeMillis())
        #else
        return Int(CFAbsoluteTimeGetCurrent() * 1000)
        #endif
    }

    func delayedInt(millis: Int) async throws -> Int {
        try await Task.sleep(nanoseconds: UInt64(1_000_000 * millis))
        return millis
    }

    func asyncInt() async -> Int {
        return 100
    }

    func asyncInt2() async -> Int {
        return 200
    }
}

struct ConcurrencyTestsError: Error {
}

struct AsyncIntSequence: AsyncSequence {
    typealias AsyncIterator = Iterator
    typealias Element = Int

    func makeAsyncIterator() -> Iterator {
        return Iterator()
    }

    class Iterator: AsyncIteratorProtocol {
        var iterations = 0

        func next() async -> Int? {
            guard iterations < 3 else {
                return nil
            }
            let result = iterations * 100
            iterations += 1
            return result
        }
    }
}
