// Copyright 2023 Skip
//
// This is free software: you can redistribute and/or modify it
// under the terms of the GNU Lesser General Public License 3.0
// as published by the Free Software Foundation https://fsf.org
import XCTest

final class StringTests: XCTestCase {
    func testCreation() {
        let str1 = "Hello, world!"
        XCTAssertEqual(str1, "Hello, world!")

        let str2 = String(repeating: "a", count: 5)
        XCTAssertEqual(str2, "aaaaa")

        let str3 = "Hello, world!".reversed()
        XCTAssertEqual(String(str3), "!dlrow ,olleH")
    }

    func testCharacterFunctions() {
        let str1 = "Hello, world!"

        let first = str1.first
        XCTAssertEqual(first, Character("H"))

        let last = str1.last
        XCTAssertEqual(last, Character("!"))
    }

    func testMultibyteCharacterFunctions() {
        let str1 = "你好，世界"

        let first = str1.first
        XCTAssertEqual(first, Character("你"))

        let firstLast = str1.reversed().last
        XCTAssertEqual(first, firstLast)

        let last = str1.last

        #if !SKIP
        XCTAssertEqual(last, "界")
        XCTAssertEqual(last?.isASCII, false)
        XCTAssertEqual(last?.isCased, false)
        XCTAssertEqual(last?.isCurrencySymbol, false)
        XCTAssertEqual(last?.isHexDigit, false)
        XCTAssertEqual(last?.isLetter, true)
        XCTAssertEqual(last?.isLowercase, false)
        XCTAssertEqual(last?.isUppercase, false)
        XCTAssertEqual(last?.isMathSymbol, false)
        XCTAssertEqual(last?.isNewline, false)
        XCTAssertEqual(last?.isNumber, false)
        XCTAssertEqual(last?.isPunctuation, false)
        XCTAssertEqual(last?.isHexDigit, false)
        XCTAssertEqual(last?.isCurrencySymbol, false)
        XCTAssertEqual(last?.isWholeNumber, false)
        XCTAssertEqual(last?.wholeNumberValue, nil)
        #endif
        XCTAssertEqual(last, Character("界"))

        let lastFirst = str1.reversed().first
        XCTAssertEqual(last, lastFirst)
    }

    func testManipulation() {
        var str = "Hello, world!"
        XCTAssertEqual(str.count, 13)

        XCTAssertEqual(str.isEmpty, false)

        #if !SKIP
        str.append(contentsOf: " How are you?")
        XCTAssertEqual(str, "Hello, world! How are you?")

        str.removeLast(13)
        XCTAssertEqual(str, "Hello, world!")
        #endif

        let index = str.firstIndex(of: ",")!
        let substring = str[..<index]
        XCTAssertEqual(String(substring), "Hello")
    }

    func testStringSearching() {
        let str = "The quick brown fox jumps over the lazy dog."

        XCTAssertTrue(str.hasPrefix("The"))
        XCTAssertTrue(str.hasSuffix("."))
        XCTAssertTrue(str.contains("fox"))

        let index = str.firstIndex(of: "b")!
        XCTAssertEqual(str.distance(from: str.startIndex, to: index), 10)
    }

    func testFirstDropFirst() {
        let str = "hello, world!"
        let firstChar = str.first
        XCTAssertEqual(firstChar?.description, "h")
        let rest = str.dropFirst()
        XCTAssertEqual(rest, "ello, world!")
        let rest2 = str.dropFirst(2)
        XCTAssertEqual(rest2, "llo, world!")
        let frst = str.dropLast()
        XCTAssertEqual(frst, "hello, world")
        let frst2 = str.dropLast(2)
        XCTAssertEqual(frst2, "hello, worl")
    }

    func testMultlineStrings() {
        let str = """
        Hello there,

        How do you do?

            Bye!
        """
        let str2 = "Hello there,\n\nHow do you do?\n\n    Bye!"
        XCTAssertEqual(str, str2)
    }

    func testSlice() {
        let str = "abcdef"
        let bindex: String.Index = str.firstIndex(of: "b")!
        let char = str[bindex]
        XCTAssertTrue(char == "b")

        let sub1 = str[bindex..<str.index(str.startIndex, offsetBy: 3)]
        XCTAssertEqual(String(sub1), "bc")

        let sub2 = str[sub1.startIndex...sub1.endIndex]
        XCTAssertEqual(String(sub2), "bcd")

        let str2 = str + sub2
        XCTAssertEqual(str2, "abcdefbcd")
    }

    func testUnicodeStrings() {
        #if SKIP
        throw XCTSkip("TODO: testUnicodeStrings")
        #else
        // Illegal escape: '\u'
        XCTAssertEqual("Bu\u{00f1}uelos", "Bun\u{0303}uelos")

        let character1 = "É"
        let character2 = "E\u{0301}"
        XCTAssertEqual(character1, character2)

        let alpha1 = "α"
        let alpha2 = "\u{03B1}"
        XCTAssertEqual(alpha1, alpha2)

        let beta1 = "β"
        let beta2 = "\u{03B2}"
        XCTAssertEqual(beta1, beta2)

        let squareRoot1 = "√"
        let squareRoot2 = "\u{221A}"
        XCTAssertEqual(squareRoot1, squareRoot2)

        let notEqual1 = "≠"
        let notEqual2 = "\u{2260}"
        XCTAssertEqual(notEqual1, notEqual2)

        let emoji1 = "👨‍💻"
        let emoji2 = "👨\u{200D}💻"
        XCTAssertEqual(emoji1, emoji2)

        let flagUS1 = "🇺🇸"
        let flagUS2 = "\u{1F1FA}\u{1F1F8}"
        XCTAssertEqual(flagUS1, flagUS2)

        let flagFrance1 = "🇫🇷"
        let flagFrance2 = "\u{1F1EB}\u{1F1F7}"
        XCTAssertEqual(flagFrance1, flagFrance2)

        let word1 = "café"
        let word2 = "cafe\u{301}"
        XCTAssertEqual(word1, word2)

        let mixed1 = "Hello 世界!"
        let mixed2 = "Hello \u{4E16}\u{754C}!"
        XCTAssertEqual(mixed1, mixed2)

        let hiragana1 = "ひらがな"
        let hiragana2 = "\u{3072}\u{3089}\u{304C}\u{306A}"
        XCTAssertEqual(hiragana1, hiragana2)

        let katakana1 = "カタカナ"
        let katakana2 = "\u{30AB}\u{30BF}\u{30AB}\u{30CA}"
        XCTAssertEqual(katakana1, katakana2)

        let dollar1 = "$"
        let dollar2 = "\u{0024}"
        XCTAssertEqual(dollar1, dollar2)

        let euro1 = "€"
        let euro2 = "\u{20AC}"
        XCTAssertEqual(euro1, euro2)

        let alef1 = "ا"
        let alef2 = "\u{0627}"
        XCTAssertEqual(alef1, alef2)

        let beh1 = "ب"
        let beh2 = "\u{0628}"
        XCTAssertEqual(beh1, beh2)

        let smiley1 = "😊"
        let smiley2 = "\u{1F60A}"
        XCTAssertEqual(smiley1, smiley2)

        let heart1 = "💖"
        let heart2 = "\u{1F496}"
        XCTAssertEqual(heart1, heart2)

        // combining characters
        let oU1 = "Ǔ"
        let oU2 = "U\u{030C}"
        XCTAssertEqual(oU1, oU2)
        #endif
    }

    func testSplitJoin() {
        let str = "ab,cd,efg,,hi"
        let arr = str.split(separator: ",")
        XCTAssertEqual(arr.count, 4)

        let str2 = arr.joined(separator: "++")
        XCTAssertEqual(str2, "ab++cd++efg++hi")
    }

    func testStringFormat() {
        XCTAssertEqual(String(format: "%%"), "%") // Escaping percent sign
        XCTAssertEqual(String(format: "%d", 42), "42") // Integer format
        XCTAssertEqual(String(format: "%f", 3.14159), "3.141590") // Float format
        XCTAssertEqual(String(format: "%.2f", 3.14159), "3.14") // Float format with precision
        XCTAssertEqual(String(format: "%5d", 42), "   42") // Padding
        XCTAssertEqual(String(format: "%.3f", 2.71828), "2.718") // Float format with precision
        XCTAssertEqual(String(format: "%.2f%%", 75.0), "75.00%") // Percent format
        XCTAssertEqual(String(format: "%3$d %2$d %1$d", 1, 2, 3), "3 2 1") // Argument reordering
        XCTAssertEqual(String(format: "%3$d %2$d %d", 1, 2, 3, 4), "3 2 1") // Extra arguments ignored
        //XCTAssertNil(String(format: "%@")) // Missing argument

        #if !SKIP // java.util.UnknownFormatConversionException: Conversion = '@'
        XCTAssertEqual(String(format: "Name: %@, Age: %d", "Alice", 30), "Name: Alice, Age: 30") // Mixed formats
        XCTAssertEqual(String(format: "%@ %d", arguments: ["Answer:", 42]), "Answer: 42") // Arguments in an array
        XCTAssertEqual(String(format: "Hello, %@", "world"), "Hello, world") // Basic substitution
        XCTAssertEqual(String(format: "%@, %@", "Hello", "world"), "Hello, world") // Multiple substitutions
        //XCTAssertEqual(String(format: "%%@ %s %d", "String", 42), "%@ %s 42") // Mixed literal and format specifiers
        XCTAssertEqual(String(format: "%@%@%@%@", "a", "b", "c", "d"), "abcd") // Multiple %@ substitutions
        XCTAssertEqual(String(format: "%1$@ %2$@ %1$@", "A", "B"), "A B A") // Reusing arguments
        //XCTAssertEqual(String(format: "%5$@ %1$d %4$@", 42, "hello", 3.14159, "world"), "world 42 3.14159") // Mixed arguments
        //XCTAssertEqual(String(format: "The %@ is %@: %2$d", "answer", "forty-two"), "The answer is forty-two: 42") // Mixed substitutions
        #endif

        #if !SKIP // java.util.UnknownFormatConversionException: Conversion = '.'
        XCTAssertEqual(String(format: "%.*f", 3, 3.14159), "3.142") // Precision with variable argument
        XCTAssertEqual(String(format: "%1$.*2$f", 3.14159, 3), "3.142") // Dynamic width and precision
        #endif

        XCTAssertEqual(String(format: "The answer is %d", arguments: [42]), "The answer is 42") // Argument in array
        XCTAssertEqual(String(format: "The %% is not replaced: %%%d", 42), "The % is not replaced: %42") // Escaping and substitution

        XCTAssertEqual(String(format: "The answer is %d", 42), "The answer is 42") // Basic integer substitution

        #if !SKIP // java.util.UnknownFormatConversionException: Conversion = 'z'
        XCTAssertEqual(String(format: "The answer is %zd", 42), "The answer is 42") // Basic integer substitution (alternative specifier)
        XCTAssertEqual(String(format: "The value is %u", -42), "The value is 4294967254") // Unsigned format (negative number)
        XCTAssertEqual(String(format: "The answer is %05d", 42), "The answer is 00042") // Zero padding
        #endif

        XCTAssertEqual(String(format: "The answer is %x", 42), "The answer is 2a") // Hexadecimal format
        XCTAssertEqual(String(format: "The answer is %o", 42), "The answer is 52") // Octal format
        XCTAssertEqual(String(format: "The answer is %+d", 42), "The answer is +42") // Positive sign
        XCTAssertEqual(String(format: "The answer is % d", 42), "The answer is  42") // Space for positive sign
        XCTAssertEqual(String(format: "The answer is %10.2f", 3.14159), "The answer is       3.14") // Width and precision
        XCTAssertEqual(String(format: "The value is %+.2e", 12345.6789), "The value is +1.23e+04") // Exponential notation
        XCTAssertEqual(String(format: "The value is %#.2f", 123.45), "The value is 123.45") // No effect of # flag on float
        XCTAssertEqual(String(format: "The value is %#x", 42), "The value is 0x2a") // Hex format with # flag
        XCTAssertEqual(String(format: "The value is %02x", 42), "The value is 2a") // No effect of 0 flag on hex

        #if SKIP
        XCTAssertEqual(String(format: "The value is %.0f", 42.5), "The value is 43") // Different rounding in Java
        #else
        XCTAssertEqual(String(format: "The value is %.0f", 42.5), "The value is 42") // No effect of 0 precision on float
        #endif
    }
}
