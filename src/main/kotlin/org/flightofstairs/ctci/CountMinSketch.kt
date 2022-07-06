package org.flightofstairs.ctci

import kotlin.math.absoluteValue
import kotlin.math.ceil
import kotlin.math.ln

// Warning: Do not use this in production. Default hashing is not even close to uniform.
class CountMinSketch<T>(
    epsilon: Double,
    delta: Double,
    private val hashFunctions: (T) -> Pair<Int, Int> = { defaultHashFunction(it) }
) {
    private val columns = ceil(Math.E/epsilon).toInt()
    private val rows = ceil(ln(1/delta)).toInt()

    private val counts = IntArray(columns * rows)

    private fun indices(item: T): IntArray {
        val (hash1, hash2) = hashFunctions(item)

        // Kirsch-Mitzenmacher Optimization to derive k hash functions from just two.
        return (0 until rows).map { row ->
            val hashResult = (hash1 + row * hash2).absoluteValue % columns
            hashResult + row * columns
        }.toIntArray()
    }

    fun add(item: T) {
        indices(item).forEach {
            counts[it]++
        }
    }

    fun frequencyUpperBound(item: T) = indices(item).minOf {
        counts[it]
    }
}

// This is a terrible simplification. In reality, both hash-codes should be independent. This can't be done if the only
// means to hash an item is via `hashCode()`.
private fun <T> defaultHashFunction(item: T) = item.hashCode() to item.hashCode()
