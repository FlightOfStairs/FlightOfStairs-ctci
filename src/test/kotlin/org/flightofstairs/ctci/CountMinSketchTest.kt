package org.flightofstairs.ctci

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CountMinSketchTest {

    @Test
    fun countEmpty() {
        CountMinSketch<String>(0.1, 0.1).frequencyUpperBound("foo") shouldBe 0
    }

    @Test
    fun singleDistinctItem() {
        val sketch = CountMinSketch<String>(0.1, 0.1)
        sketch.add("foo")
        sketch.add("foo")
        sketch.frequencyUpperBound("foo") shouldBe 2
    }

    @Test
    fun collisions() {
        val sketch = CountMinSketch<String>(0.02, 0.01)

        (0 until 100).forEach {
            sketch.add("$it-item")
        }

        val upperBoundCounts = (0 until 100).map {
            sketch.frequencyUpperBound("$it-item")
        }.groupingBy { it }.eachCount()

        // May change if String.hashCode() changes
        upperBoundCounts[1] shouldBe 85
        upperBoundCounts[2] shouldBe 15
    }

    @Test
    fun collisionsButBetterHashing() {
        // This hashing is much better than default as functions are now different, but still far from ideal.
        val hashFunctions: (String) -> Pair<Int, Int> = { item ->
            val hash1 = item.hashCode()
            val hash2 = "$hash1$item".hashCode()

            hash1 to hash2
        }

        val sketch = CountMinSketch(0.02, 0.01, hashFunctions)

        (0 until 100).forEach {
            sketch.add("$it-item")
        }

        val upperBoundCounts = (0 until 100).map {
            sketch.frequencyUpperBound("$it-item")
        }.groupingBy { it }.eachCount()

        upperBoundCounts[1] shouldBe 98
        upperBoundCounts[2] shouldBe 2
    }
}
