// Copyright 2023 Skip
//
// This is free software: you can redistribute and/or modify it
// under the terms of the GNU Lesser General Public License 3.0
// as published by the Free Software Foundation https://fsf.org

// SKIP SYMBOLFILE

#if SKIP

import androidx.compose.runtime.mutableStateOf

extension Bool: CustomStringConvertible {
    public static func random() -> Bool {
        // Generate a random Boolean value
        return SecureRandom().nextBoolean()
    }
    public static func random(using gen: inout RandomNumberGenerator) -> Bool {
        return SecureRandom().nextBoolean()
    }
    public var description: String {
        return toString()
    }

    public mutating func toggle() {
        // Since Booleans are immutable in Kotlin, we use a MutableBoolean class to handle toggling.
        // You can call this method on a MutableBoolean instance
        value = !value
    }
}

// Wrapper class for mutable Boolean with toggle functionality
class MutableBoolean {
    func toggle() {
        value = !value
    }

    override func toString() {
        return value.toString()
    }
}

#endif
