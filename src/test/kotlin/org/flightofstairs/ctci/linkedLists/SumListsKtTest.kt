package org.flightofstairs.ctci.linkedLists

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class SumListsKtTest {
    @Test
    fun example() {
        sumLists(listOf(7, 1, 6).toNode(), listOf(5, 9, 2).toNode()).toList() shouldBe listOf(2, 1, 9)
    }

    @Test
    fun leftEmpty() {
        sumLists(listOf<Int>().toNode(), listOf(5, 9, 2).toNode()).toList() shouldBe listOf(5, 9, 2)
    }

    @Test
    fun rightEmpty() {
        sumLists(listOf(7, 1, 6).toNode(), listOf<Int>().toNode()).toList() shouldBe listOf(7, 1, 6)
    }

    @Test
    fun unevenWithCarry() {
        sumLists(listOf(9).toNode(), listOf(9, 1, 1).toNode()).toList() shouldBe listOf(8, 2, 1)
    }

    @Test
    fun unevenWithCarryFlipped() {
        sumLists(listOf(9, 1, 1).toNode(), listOf(9).toNode()).toList() shouldBe listOf(8, 2, 1)
    }

    @Test
    fun exampleReversed() {
        sumListsReversed(listOf(6, 1, 7).toNode(), listOf(2, 9, 5).toNode()).toList() shouldBe listOf(9, 1, 2)
    }

    @Test
    fun leftEmptyReversed() {
        sumListsReversed(listOf<Int>().toNode(), listOf(2, 9, 5).toNode()).toList() shouldBe listOf(2, 9, 5)
    }

    @Test
    fun rightEmptyReversed() {
        sumListsReversed(listOf(6, 1, 7).toNode(), listOf<Int>().toNode()).toList() shouldBe listOf(6, 1, 7)
    }

    @Test
    fun unevenWithCarryReversed() {
        sumListsReversed(listOf(9).toNode(), listOf(1, 1, 9).toNode()).toList() shouldBe listOf(1, 2, 8)
    }

    @Test
    fun unevenWithCarryFlippedReversed() {
        sumListsReversed(listOf(1, 1, 9).toNode(), listOf(9).toNode()).toList() shouldBe listOf(1, 2, 8)
    }
}
