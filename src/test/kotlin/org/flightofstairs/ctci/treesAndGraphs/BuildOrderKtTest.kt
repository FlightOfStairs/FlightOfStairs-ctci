package org.flightofstairs.ctci.treesAndGraphs

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BuildOrderKtTest {

    @Test
    fun empty() {
        buildOrder(setOf(), setOf()) shouldBe emptyList()
    }

    @Test
    fun impossible() {
        shouldThrow<NoValidBuildOrderException> {
            buildOrder(setOf("A", "B", "C"), setOf("A" to "B", "B" to "C", "C" to "A"))
        }
    }

    @Test
    fun noDependencies() {
        buildOrder(setOf("A", "B", "C"), emptySet()) shouldBe listOf("A", "B", "C")
    }

    @Test
    fun example() {
        val dependencies = setOf("a" to "d", "f" to "b", "b" to "d", "f" to "a", "d" to "c")
        val buildOrder = buildOrder("abcdef".chunked(1).toSet(), dependencies)

        // This is not a unique solution to the problem. It may change if hashcode, map, or set implementations change.
        buildOrder shouldBe "efbadc".chunked(1)
    }
}
