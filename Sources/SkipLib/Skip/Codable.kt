// Copyright 2023 Skip
//
// This is free software: you can redistribute and/or modify it
// under the terms of the GNU Lesser General Public License 3.0
// as published by the Free Software Foundation https://fsf.org
package skip.lib

import kotlin.reflect.KClass

interface Codable: Decodable, Encodable {
}

interface Encodable {
    fun encode(to: Encoder)
}

interface Encoder {
    val codingPath: Array<CodingKey>
    val userInfo: Dictionary<CodingUserInfoKey, Any>
    fun <Key> container(keyedBy: KClass<Key>): KeyedEncodingContainer<Key> where Key: CodingKey
    fun unkeyedContainer(): UnkeyedEncodingContainer
    fun singleValueContainer(): SingleValueEncodingContainer
}

interface KeyedEncodingContainerProtocol {
    val codingPath: Array<CodingKey>
    fun encodeNil(forKey: CodingKey)
    fun encode(value: Boolean, forKey: CodingKey)
    fun encode(value: String, forKey: CodingKey)
    fun encode(value: Double, forKey: CodingKey)
    fun encode(value: Float, forKey: CodingKey)
    fun encode(value: Byte, forKey: CodingKey)
    fun encode(value: Short, forKey: CodingKey)
    fun encode(value: Int, forKey: CodingKey)
    fun encode(value: Long, forKey: CodingKey)
    fun encode(value: UByte, forKey: CodingKey)
    fun encode(value: UShort, forKey: CodingKey)
    fun encode(value: UInt, forKey: CodingKey)
    fun encode(value: ULong, forKey: CodingKey)
    fun <T> encode(value: T, forKey: CodingKey) where T: Encodable

    fun <T> encodeConditional(object_: T, forKey: CodingKey) where T: Encodable {
        encode(object_, forKey)
    }

    fun encodeIfPresent(value: Boolean?, forKey: CodingKey) {
        if (value != null) encode(value, forKey)
    }

    fun encodeIfPresent(value: String?, forKey: CodingKey) {
        if (value != null) encode(value, forKey)
    }

    fun encodeIfPresent(value: Double?, forKey: CodingKey) {
        if (value != null) encode(value, forKey)
    }

    fun encodeIfPresent(value: Float?, forKey: CodingKey) {
        if (value != null) encode(value, forKey)
    }

    fun encodeIfPresent(value: Byte?, forKey: CodingKey) {
        if (value != null) encode(value, forKey)
    }

    fun encodeIfPresent(value: Short?, forKey: CodingKey) {
        if (value != null) encode(value, forKey)
    }

    fun encodeIfPresent(value: Int?, forKey: CodingKey) {
        if (value != null) encode(value, forKey)
    }

    fun encodeIfPresent(value: Long?, forKey: CodingKey) {
        if (value != null) encode(value, forKey)
    }

    fun encodeIfPresent(value: UByte?, forKey: CodingKey) {
        if (value != null) encode(value, forKey)
    }

    fun encodeIfPresent(value: UShort?, forKey: CodingKey) {
        if (value != null) encode(value, forKey)
    }

    fun encodeIfPresent(value: UInt?, forKey: CodingKey) {
        if (value != null) encode(value, forKey)
    }

    fun encodeIfPresent(value: ULong?, forKey: CodingKey) {
        if (value != null) encode(value, forKey)
    }

    fun <T> encodeIfPresent(value: T?, forKey: CodingKey) where T: Encodable {
        if (value != null) encode(value, forKey)
    }

    fun <NestedKey> nestedContainer(keyedBy: KClass<NestedKey>, forKey: CodingKey): KeyedEncodingContainer<NestedKey> where NestedKey: CodingKey
    fun nestedUnkeyedContainer(forKey: CodingKey): UnkeyedEncodingContainer
    fun superEncoder(): Encoder
    fun superEncoder(forKey: CodingKey): Encoder
}

class KeyedEncodingContainer<Key>(container: KeyedEncodingContainerProtocol) : KeyedEncodingContainerProtocol where Key: CodingKey {
    private val box: KeyedEncodingContainerProtocol = container

    override val codingPath: Array<CodingKey>
        get() = box.codingPath

    override fun encodeNil(forKey: CodingKey) {
        box.encodeNil(forKey)
    }

    override fun encode(value: Boolean, forKey: CodingKey) {
        box.encode(value, forKey)
    }

    override fun encode(value: String, forKey: CodingKey) {
        box.encode(value, forKey)
    }

    override fun encode(value: Double, forKey: CodingKey) {
        box.encode(value, forKey)
    }

    override fun encode(value: Float, forKey: CodingKey) {
        box.encode(value, forKey)
    }

    override fun encode(value: Byte, forKey: CodingKey) {
        box.encode(value, forKey)
    }

    override fun encode(value: Short, forKey: CodingKey) {
        box.encode(value, forKey)
    }

    override fun encode(value: Int, forKey: CodingKey) {
        box.encode(value, forKey)
    }

    override fun encode(value: Long, forKey: CodingKey) {
        box.encode(value, forKey)
    }

    override fun encode(value: UByte, forKey: CodingKey) {
        box.encode(value, forKey)
    }

    override fun encode(value: UShort, forKey: CodingKey) {
        box.encode(value, forKey)
    }

    override fun encode(value: UInt, forKey: CodingKey) {
        box.encode(value, forKey)
    }

    override fun encode(value: ULong, forKey: CodingKey) {
        box.encode(value, forKey)
    }

    override fun <T> encode(value: T, forKey: CodingKey) where T: Encodable {
        box.encode(value, forKey)
    }

    override fun <T> encodeConditional(object_: T, forKey: CodingKey) where T: Encodable {
        box.encodeConditional(object_, forKey)
    }

    override fun encodeIfPresent(value: Boolean?, forKey: CodingKey) {
        box.encodeIfPresent(value, forKey)
    }

    override fun encodeIfPresent(value: String?, forKey: CodingKey) {
        box.encodeIfPresent(value, forKey)
    }

    override fun encodeIfPresent(value: Double?, forKey: CodingKey) {
        box.encodeIfPresent(value, forKey)
    }

    override fun encodeIfPresent(value: Float?, forKey: CodingKey) {
        box.encodeIfPresent(value, forKey)
    }

    override fun encodeIfPresent(value: Byte?, forKey: CodingKey) {
        box.encodeIfPresent(value, forKey)
    }

    override fun encodeIfPresent(value: Short?, forKey: CodingKey) {
        box.encodeIfPresent(value, forKey)
    }

    override fun encodeIfPresent(value: Int?, forKey: CodingKey) {
        box.encodeIfPresent(value, forKey)
    }

    override fun encodeIfPresent(value: Long?, forKey: CodingKey) {
        box.encodeIfPresent(value, forKey)
    }

    override fun encodeIfPresent(value: UByte?, forKey: CodingKey) {
        box.encodeIfPresent(value, forKey)
    }

    override fun encodeIfPresent(value: UShort?, forKey: CodingKey) {
        box.encodeIfPresent(value, forKey)
    }

    override fun encodeIfPresent(value: UInt?, forKey: CodingKey) {
        box.encodeIfPresent(value, forKey)
    }

    override fun encodeIfPresent(value: ULong?, forKey: CodingKey) {
        box.encodeIfPresent(value, forKey)
    }

    override fun <T> encodeIfPresent(value: T?, forKey: CodingKey) where T: Encodable {
        box.encodeIfPresent(value, forKey)
    }

    override fun <NestedKey> nestedContainer(keyedBy: KClass<NestedKey>, forKey: CodingKey): KeyedEncodingContainer<NestedKey> where NestedKey: CodingKey {
        return box.nestedContainer(keyedBy, forKey)
    }

    override fun nestedUnkeyedContainer(forKey: CodingKey): UnkeyedEncodingContainer {
        return box.nestedUnkeyedContainer(forKey)
    }

    override fun superEncoder(): Encoder {
        return box.superEncoder()
    }

    override fun superEncoder(forKey: CodingKey): Encoder {
        return box.superEncoder(forKey)
    }

    // In Swift, Arrays and Dictionaries are Codable. In Kotlin, we cover them here to overcome generic type erasure

    fun encode(value: Sequence<*>, forKey: CodingKey) {
        val container = nestedUnkeyedContainer(forKey)
        container.encode(contentsOf = value)
    }

    fun encodeIfPresent(value: Sequence<*>?, forKey: CodingKey) {
        if (value != null) encode(value, forKey)
    }

    inline fun <reified K, V> encode(value: Dictionary<K, V>, forKey: CodingKey) where K: Any, V: Any {
        if (K::class == Int::class || K::class == String::class) {
            encodeAsDictionary(value, forKey)
        } else {
            encodeAsArray(value, forKey)
        }
    }

    inline fun <reified K, V> encodeIfPresent(value: Dictionary<K, V>?, forKey: CodingKey) {
        if (value != null) encode(value, forKey)
    }

    fun encodeAsDictionary(value: Dictionary<*, *>, forKey: CodingKey) {
        val container = nestedContainer(keyedBy = DictionaryCodingKey::class, forKey)
        for ((dkey, dvalue) in value.storage) {
            when (dvalue) {
                is Boolean -> container.encode(dvalue, forKey = DictionaryCodingKey(dkey.toString()))
                is String -> container.encode(dvalue, forKey = DictionaryCodingKey(dkey.toString()))
                is Byte -> container.encode(dvalue, forKey = DictionaryCodingKey(dkey.toString()))
                is Short -> container.encode(dvalue, forKey = DictionaryCodingKey(dkey.toString()))
                is Int -> container.encode(dvalue, forKey = DictionaryCodingKey(dkey.toString()))
                is Long -> container.encode(dvalue, forKey = DictionaryCodingKey(dkey.toString()))
                is UByte -> container.encode(dvalue, forKey = DictionaryCodingKey(dkey.toString()))
                is UShort -> container.encode(dvalue, forKey = DictionaryCodingKey(dkey.toString()))
                is UInt -> container.encode(dvalue, forKey = DictionaryCodingKey(dkey.toString()))
                is ULong -> container.encode(dvalue, forKey = DictionaryCodingKey(dkey.toString()))
                is Float -> container.encode(dvalue, forKey = DictionaryCodingKey(dkey.toString()))
                is Double -> container.encode(dvalue, forKey = DictionaryCodingKey(dkey.toString()))
                is Encodable -> container.encode(dvalue, forKey = DictionaryCodingKey(dkey.toString()))
                else -> {
                    if (dvalue is Sequence<*>) {
                        val dvalueContainer = container.nestedUnkeyedContainer(forKey = DictionaryCodingKey(dkey.toString()))
                        dvalueContainer.encode(contentsOf = dvalue)
                    } else {
                        fatalError("KeyedEncodingContainer: unhandled encode for Dictionary<*, $dvalue>")
                    }
                }
            }
        }
    }

    fun <K, V> encodeAsArray(value: Dictionary<K, V>, forKey: CodingKey) where K: Any, V: Any {
        val container = nestedUnkeyedContainer(forKey)
        for ((dkey, dvalue) in value.storage) {
            encodeAny(dkey, container)
            encodeAny(dvalue, container)
        }
    }

    fun encodeAny(value: Any, container: UnkeyedEncodingContainer) {
        when (value) {
            is Boolean -> container.encode(value)
            is String -> container.encode(value)
            is Byte -> container.encode(value)
            is Short -> container.encode(value)
            is Int -> container.encode(value)
            is Long -> container.encode(value)
            is UByte -> container.encode(value)
            is UShort -> container.encode(value)
            is UInt -> container.encode(value)
            is ULong -> container.encode(value)
            is Float -> container.encode(value)
            is Double -> container.encode(value)
            is Encodable -> container.encode(value)
            else -> {
                if (value is Sequence<*>) {
                    val valueContainer = container.nestedUnkeyedContainer()
                    valueContainer.encode(contentsOf = value)
                } else {
                    fatalError("Unhandled unkeyed encoding for $value")
                }
            }
        }
    }
}

interface UnkeyedEncodingContainer {
    val codingPath: Array<CodingKey>
    val count: Int
    fun encodeNil()
    fun encode(value: Boolean)
    fun encode(value: String)
    fun encode(value: Double)
    fun encode(value: Float)
    fun encode(value: Byte)
    fun encode(value: Short)
    fun encode(value: Int)
    fun encode(value: Long)
    fun encode(value: UByte)
    fun encode(value: UShort)
    fun encode(value: UInt)
    fun encode(value: ULong)
    fun <T> encode(value: T) where T: Encodable

    fun <T> encodeConditional(object_: T) where T: Encodable {
        encode(object_)
    }

    fun encode(contentsOf: Sequence<*>) {
        for (element in contentsOf.iterable) {
            when (element) {
                is String -> encode(element)
                is Boolean -> encode(element)
                is Byte -> encode(element)
                is Short -> encode(element)
                is Int -> encode(element)
                is Long -> encode(element)
                is UByte -> encode(element)
                is UShort -> encode(element)
                is UInt -> encode(element)
                is ULong -> encode(element)
                is Float -> encode(element)
                is Double -> encode(element)
                is Encodable -> encode(element)
                else -> {
                    if (element is Sequence<*>) {
                        val container = nestedUnkeyedContainer()
                        container.encode(contentsOf = element)
                    } else {
                        fatalError("KeyedEncodingContainerProtocol: unhandled encode for $element")
                    }
                }
            }
        }
    }

    fun <NestedKey> nestedContainer(keyedBy: KClass<NestedKey>): KeyedEncodingContainer<NestedKey> where NestedKey: CodingKey
    fun nestedUnkeyedContainer(): UnkeyedEncodingContainer
    fun superEncoder(): Encoder
}

interface SingleValueEncodingContainer {
    val codingPath: Array<CodingKey>
    fun encodeNil()
    fun encode(value: Boolean)
    fun encode(value: String)
    fun encode(value: Double)
    fun encode(value: Float)
    fun encode(value: Byte)
    fun encode(value: Short)
    fun encode(value: Int)
    fun encode(value: Long)
    fun encode(value: UByte)
    fun encode(value: UShort)
    fun encode(value: UInt)
    fun encode(value: ULong)
    fun <T> encode(value: T) where T: Encodable
}

interface Decodable {
}

interface DecodableCompanion<Owner> {
    fun init(from: Decoder): Owner
}

interface Decoder {
    val codingPath: Array<CodingKey>
    val userInfo: Dictionary<CodingUserInfoKey, Any>
    fun <Key> container(keyedBy: KClass<Key>): KeyedDecodingContainer<Key> where Key: CodingKey
    fun unkeyedContainer(): UnkeyedDecodingContainer
    fun singleValueContainer(): SingleValueDecodingContainer
}

interface KeyedDecodingContainerProtocol {
    val codingPath: Array<CodingKey>
    val allKeys: Array<CodingKey>
    fun contains(key: CodingKey): Boolean
    fun decodeNil(forKey: CodingKey): Boolean
    fun decode(type: KClass<Boolean>, forKey: CodingKey): Boolean
    fun decode(type: KClass<String>, forKey: CodingKey): String
    fun decode(type: KClass<Double>, forKey: CodingKey): Double
    fun decode(type: KClass<Float>, forKey: CodingKey): Float
    fun decode(type: KClass<Byte>, forKey: CodingKey): Byte
    fun decode(type: KClass<Short>, forKey: CodingKey): Short
    fun decode(type: KClass<Int>, forKey: CodingKey): Int
    fun decode(type: KClass<Long>, forKey: CodingKey): Long
    fun decode(type: KClass<UByte>, forKey: CodingKey): UByte
    fun decode(type: KClass<UShort>, forKey: CodingKey): UShort
    fun decode(type: KClass<UInt>, forKey: CodingKey): UInt
    fun decode(type: KClass<ULong>, forKey: CodingKey): ULong
    fun <T> decodeDecodable(type: KClass<T>, forKey: CodingKey): T where T: Any

    fun <T> decode(type: KClass<T>, forKey: CodingKey): T where T: Decodable {
        return decodeDecodable(type, forKey)
    }

    fun decodeIfPresent(type: KClass<Boolean>, forKey: CodingKey): Boolean? {
        return if (contains(forKey)) decode(type, forKey) else null
    }

    fun decodeIfPresent(type: KClass<String>, forKey: CodingKey): String? {
        return if (contains(forKey)) decode(type, forKey) else null
    }

    fun decodeIfPresent(type: KClass<Double>, forKey: CodingKey): Double? {
        return if (contains(forKey)) decode(type, forKey) else null
    }

    fun decodeIfPresent(type: KClass<Float>, forKey: CodingKey): Float? {
        return if (contains(forKey)) decode(type, forKey) else null
    }

    fun decodeIfPresent(type: KClass<Byte>, forKey: CodingKey): Byte? {
        return if (contains(forKey)) decode(type, forKey) else null
    }

    fun decodeIfPresent(type: KClass<Short>, forKey: CodingKey): Short? {
        return if (contains(forKey)) decode(type, forKey) else null
    }

    fun decodeIfPresent(type: KClass<Int>, forKey: CodingKey): Int? {
        return if (contains(forKey)) decode(type, forKey) else null
    }

    fun decodeIfPresent(type: KClass<Long>, forKey: CodingKey): Long? {
        return if (contains(forKey)) decode(type, forKey) else null
    }

    fun decodeIfPresent(type: KClass<UByte>, forKey: CodingKey): UByte? {
        return if (contains(forKey)) decode(type, forKey) else null
    }

    fun decodeIfPresent(type: KClass<UShort>, forKey: CodingKey): UShort? {
        return if (contains(forKey)) decode(type, forKey) else null
    }

    fun decodeIfPresent(type: KClass<UInt>, forKey: CodingKey): UInt? {
        return if (contains(forKey)) decode(type, forKey) else null
    }

    fun decodeIfPresent(type: KClass<ULong>, forKey: CodingKey): ULong? {
        return if (contains(forKey)) decode(type, forKey) else null
    }

    fun <T> decodeIfPresent(type: KClass<T>, forKey: CodingKey): T? where T: Decodable {
        return if (contains(forKey)) decode(type, forKey) else null
    }

    fun <NestedKey> nestedContainer(keyedBy: KClass<NestedKey>, forKey: CodingKey): KeyedDecodingContainer<NestedKey> where NestedKey: CodingKey
    fun nestedUnkeyedContainer(forKey: CodingKey): UnkeyedDecodingContainer
    fun superDecoder(): Decoder
    fun superDecoder(forKey: CodingKey): Decoder
}

class KeyedDecodingContainer<Key>(container: KeyedDecodingContainerProtocol): KeyedDecodingContainerProtocol where Key: CodingKey {
    private val box = container

    override val codingPath: Array<CodingKey>
        get() = box.codingPath

    override val allKeys: Array<CodingKey>
        get() = box.allKeys

    override fun contains(key: CodingKey): Boolean {
        return box.contains(key)
    }

    override fun decodeNil(forKey: CodingKey): Boolean {
        return box.decodeNil(forKey)
    }

    override fun decode(type: KClass<Boolean>, forKey: CodingKey): Boolean {
        return box.decode(type, forKey)
    }

    override fun decode(type: KClass<String>, forKey: CodingKey): String {
        return box.decode(type, forKey)
    }

    override fun decode(type: KClass<Double>, forKey: CodingKey): Double {
        return box.decode(type, forKey)
    }

    override fun decode(type: KClass<Float>, forKey: CodingKey): Float {
        return box.decode(type, forKey)
    }

    override fun decode(type: KClass<Byte>, forKey: CodingKey): Byte {
        return box.decode(type, forKey)
    }

    override fun decode(type: KClass<Short>, forKey: CodingKey): Short {
        return box.decode(type, forKey)
    }

    override fun decode(type: KClass<Int>, forKey: CodingKey): Int {
        return box.decode(type, forKey)
    }

    override fun decode(type: KClass<Long>, forKey: CodingKey): Long {
        return box.decode(type, forKey)
    }

    override fun decode(type: KClass<UByte>, forKey: CodingKey): UByte {
        return box.decode(type, forKey)
    }

    override fun decode(type: KClass<UShort>, forKey: CodingKey): UShort {
        return box.decode(type, forKey)
    }

    override fun decode(type: KClass<UInt>, forKey: CodingKey): UInt {
        return box.decode(type, forKey)
    }

    override fun decode(type: KClass<ULong>, forKey: CodingKey): ULong {
        return box.decode(type, forKey)
    }

    override fun <T> decode(type: KClass<T>, forKey: CodingKey): T where T: Decodable {
        return box.decode(type, forKey)
    }

    override fun <T> decodeDecodable(type: KClass<T>, forKey: CodingKey): T where T : Any {
        return box.decodeDecodable(type, forKey)
    }

    override fun decodeIfPresent(type: KClass<Boolean>, forKey: CodingKey): Boolean? {
        return box.decodeIfPresent(type, forKey)
    }

    override fun decodeIfPresent(type: KClass<String>, forKey: CodingKey): String? {
        return box.decodeIfPresent(type, forKey)
    }

    override fun decodeIfPresent(type: KClass<Double>, forKey: CodingKey): Double? {
        return box.decodeIfPresent(type, forKey)
    }

    override fun decodeIfPresent(type: KClass<Float>, forKey: CodingKey): Float? {
        return box.decodeIfPresent(type, forKey)
    }

    override fun decodeIfPresent(type: KClass<Byte>, forKey: CodingKey): Byte? {
        return box.decodeIfPresent(type, forKey)
    }

    override fun decodeIfPresent(type: KClass<Short>, forKey: CodingKey): Short? {
        return box.decodeIfPresent(type, forKey)
    }

    override fun decodeIfPresent(type: KClass<Int>, forKey: CodingKey): Int? {
        return box.decodeIfPresent(type, forKey)
    }

    override fun decodeIfPresent(type: KClass<Long>, forKey: CodingKey): Long? {
        return box.decodeIfPresent(type, forKey)
    }

    override fun decodeIfPresent(type: KClass<UByte>, forKey: CodingKey): UByte? {
        return box.decodeIfPresent(type, forKey)
    }

    override fun decodeIfPresent(type: KClass<UShort>, forKey: CodingKey): UShort? {
        return box.decodeIfPresent(type, forKey)
    }

    override fun decodeIfPresent(type: KClass<UInt>, forKey: CodingKey): UInt? {
        return box.decodeIfPresent(type, forKey)
    }

    override fun decodeIfPresent(type: KClass<ULong>, forKey: CodingKey): ULong? {
        return box.decodeIfPresent(type, forKey)
    }

    override fun <T> decodeIfPresent(type: KClass<T>, forKey: CodingKey): T? where T: Decodable {
        return box.decodeIfPresent(type, forKey)
    }

    override fun <NestedKey> nestedContainer(keyedBy: KClass<NestedKey>, forKey: CodingKey): KeyedDecodingContainer<NestedKey> where NestedKey: CodingKey {
        return box.nestedContainer(keyedBy, forKey)
    }

    override fun nestedUnkeyedContainer(forKey: CodingKey): UnkeyedDecodingContainer {
        return box.nestedUnkeyedContainer(forKey)
    }

    override fun superDecoder(): Decoder {
        return box.superDecoder()
    }

    override fun superDecoder(forKey: CodingKey): Decoder {
        return box.superDecoder(forKey)
    }

    // In Swift, Arrays and Dictionaries are Codable. In Kotlin, we cover them here to overcome generic type erasure

    inline fun <reified E> decode(type: KClass<Array<E>>, forKey: CodingKey): Array<E> where E: Any {
        return decodeSequence(type = E::class, forKey, factory = { Array(it, nocopy = true)  }) as Array<E>
    }

    inline fun <reified E> decode(type: KClass<Set<E>>, forKey: CodingKey): Set<E> where E: Any {
        return decodeSequence(type = E::class, forKey, factory = { Set(it, nocopy = true)  }) as Set<E>
    }

    inline fun <reified E> decodeSequence(type: KClass<E>, forKey: CodingKey, factory: (MutableList<E>) -> Sequence<E>): Sequence<E> where E: Any {
        val decoder = unkeyedDecoder(E::class)
        val container = nestedUnkeyedContainer(forKey)
        val list: MutableList<E> = mutableListOf()
        while (!container.isAtEnd) {
            val element = decoder(container)
            list.add(element)
        }
        return factory(list)
    }

    inline fun <reified K, reified V> decode(type: KClass<Dictionary<K, V>>, forKey: CodingKey): Dictionary<K, V> where K: Any, V: Any {
        when (K::class) {
            String::class -> return decodeAsDictionary(type, forKey, key = { it.stringValue as K })
            Int::class -> return decodeAsDictionary(type, forKey, key = { (it.intValue ?: 0) as K })
            else -> return decodeAsArray(type, forKey)
        }
    }

    inline fun <K, reified V> decodeAsDictionary(type: KClass<Dictionary<K, V>>, forKey: CodingKey, key: (CodingKey) -> K): Dictionary<K, V> where V: Any {
        val decoder = keyedDecoder(V::class)
        val container = nestedContainer(keyedBy = DictionaryCodingKey::class, forKey)
        val map = LinkedHashMap<K, V>()
        for (codingKey in container.allKeys) {
            map[key(codingKey)] = decoder(container, codingKey)
        }
        return Dictionary(map, nocopy = true)
    }

    inline fun <reified K, reified V> decodeAsArray(type: KClass<Dictionary<K, V>>, forKey: CodingKey): Dictionary<K, V> where K: Any, V: Any {
        val keyDecoder = unkeyedDecoder(K::class)
        val valueDecoder = unkeyedDecoder(V::class)
        val container = nestedUnkeyedContainer(forKey)
        val map = LinkedHashMap<K, V>()
        while (!container.isAtEnd) {
            val key = keyDecoder(container)
            val value = valueDecoder(container)
            map[key] = value
        }
        return Dictionary(map, nocopy = true)
    }

    inline fun <reified T> keyedDecoder(forType: KClass<T>): (KeyedDecodingContainer<DictionaryCodingKey>, CodingKey) -> T where T: Any {
        val decoder: (KeyedDecodingContainer<DictionaryCodingKey>, CodingKey) -> T
        when (T::class) {
            Boolean::class -> decoder = { container, dkey -> container.decode(Boolean::class, dkey) as T }
            String::class -> decoder = { container, dkey -> container.decode(String::class, dkey) as T }
            Byte::class -> decoder = { container, dkey -> container.decode(Byte::class, dkey) as T }
            Short::class -> decoder = { container, dkey -> container.decode(Short::class, dkey) as T }
            Int::class -> decoder = { container, dkey -> container.decode(Int::class, dkey) as T }
            Long::class -> decoder = { container, dkey -> container.decode(Long::class, dkey) as T }
            Float::class -> decoder = { container, dkey -> container.decode(Float::class, dkey) as T }
            Double::class -> decoder = { container, dkey -> container.decode(Double::class, dkey) as T }
            UByte::class -> decoder = { container, dkey -> container.decode(UByte::class, dkey) as T }
            UShort::class -> decoder = { container, dkey -> container.decode(UShort::class, dkey) as T }
            UInt::class -> decoder = { container, dkey -> container.decode(UInt::class, dkey) as T }
            ULong::class -> decoder = { container, dkey -> container.decode(ULong::class, dkey) as T }
            else -> decoder = { container, dkey -> container.decodeDecodable(T::class, dkey) as T }
        }
        return decoder
    }

    inline fun <reified T> unkeyedDecoder(forType: KClass<T>): (UnkeyedDecodingContainer) -> T where T: Any {
        val decoder: (UnkeyedDecodingContainer) -> T
        when (T::class) {
            Boolean::class -> decoder = { it.decode(Boolean::class) as T }
            String::class -> decoder = { it.decode(String::class) as T }
            Byte::class -> decoder = { it.decode(Byte::class) as T }
            Short::class -> decoder = { it.decode(Short::class) as T }
            Int::class -> decoder = { it.decode(Int::class) as T }
            Long::class -> decoder = { it.decode(Long::class) as T }
            Float::class -> decoder = { it.decode(Float::class) as T }
            Double::class -> decoder = { it.decode(Double::class) as T }
            UByte::class -> decoder = { it.decode(UByte::class) as T }
            UShort::class -> decoder = { it.decode(UShort::class) as T }
            UInt::class -> decoder = { it.decode(UInt::class) as T }
            ULong::class -> decoder = { it.decode(ULong::class) as T }
            else -> decoder = { it.decodeDecodable(T::class) as T }
        }
        return decoder
    }
}

class DictionaryCodingKey(override val rawValue: String) : CodingKey {
}

interface UnkeyedDecodingContainer {
    val codingPath: Array<CodingKey>
    val count: Int?
    val isAtEnd: Boolean
    val currentIndex: Int
    fun decodeNil(): Boolean
    fun decode(type: KClass<Boolean>): Boolean
    fun decode(type: KClass<String>): String
    fun decode(type: KClass<Double>): Double
    fun decode(type: KClass<Float>): Float
    fun decode(type: KClass<Byte>): Byte
    fun decode(type: KClass<Short>): Short
    fun decode(type: KClass<Int>): Int
    fun decode(type: KClass<Long>): Long
    fun decode(type: KClass<UByte>): UByte
    fun decode(type: KClass<UShort>): UShort
    fun decode(type: KClass<UInt>): UInt
    fun decode(type: KClass<ULong>): ULong
    fun <T> decodeDecodable(type: KClass<T>): T where T : Any

    fun <T> decode(type: KClass<T>): T where T: Decodable {
        return decodeDecodable(type)
    }

    fun decodeIfPresent(type: KClass<Boolean>): Boolean? {
        return if (isAtEnd) null else decode(type)
    }

    fun decodeIfPresent(type: KClass<String>): String? {
        return if (isAtEnd) null else decode(type)
    }

    fun decodeIfPresent(type: KClass<Double>): Double? {
        return if (isAtEnd) null else decode(type)
    }

    fun decodeIfPresent(type: KClass<Float>): Float? {
        return if (isAtEnd) null else decode(type)
    }

    fun decodeIfPresent(type: KClass<Byte>): Byte? {
        return if (isAtEnd) null else decode(type)
    }

    fun decodeIfPresent(type: KClass<Short>): Short? {
        return if (isAtEnd) null else decode(type)
    }

    fun decodeIfPresent(type: KClass<Int>): Int? {
        return if (isAtEnd) null else decode(type)
    }

    fun decodeIfPresent(type: KClass<Long>): Long? {
        return if (isAtEnd) null else decode(type)
    }

    fun decodeIfPresent(type: KClass<UByte>): UByte? {
        return if (isAtEnd) null else decode(type)
    }

    fun decodeIfPresent(type: KClass<UShort>): UShort? {
        return if (isAtEnd) null else decode(type)
    }

    fun decodeIfPresent(type: KClass<UInt>): UInt? {
        return if (isAtEnd) null else decode(type)
    }

    fun decodeIfPresent(type: KClass<ULong>): ULong? {
        return if (isAtEnd) null else decode(type)
    }

    fun <T> decodeIfPresent(type: KClass<T>): T? where T: Decodable {
        return if (isAtEnd) null else decode(type)
    }

    fun <NestedKey> nestedContainer(keyedBy: KClass<NestedKey>): KeyedDecodingContainer<NestedKey> where NestedKey: CodingKey
    fun nestedUnkeyedContainer(): UnkeyedDecodingContainer
    fun superDecoder(): Decoder

}

interface SingleValueDecodingContainer {
    val codingPath: Array<CodingKey>
    fun decodeNil(): Boolean
    fun decode(type: KClass<Boolean>): Boolean
    fun decode(type: KClass<String>): String
    fun decode(type: KClass<Double>): Double
    fun decode(type: KClass<Float>): Float
    fun decode(type: KClass<Byte>): Byte
    fun decode(type: KClass<Short>): Short
    fun decode(type: KClass<Int>): Int
    fun decode(type: KClass<Long>): Long
    fun decode(type: KClass<UByte>): UByte
    fun decode(type: KClass<UShort>): UShort
    fun decode(type: KClass<UInt>): UInt
    fun decode(type: KClass<ULong>): ULong
    fun <T> decode(type: KClass<T>): T where T: Decodable
}