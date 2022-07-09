package org.flightofstairs.ctci.recusionAndDynamicProgramming

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class NQueensKtTest {

    @Test
    fun nQueens() {
        nQueens(8).count() shouldBe 92
    }

    @Test
    fun permutations() {
        listOf(1).permutations().toSet() shouldContainExactlyInAnyOrder setOf(
            listOf(1)
        )

        listOf(1, 2).permutations().toSet() shouldContainExactlyInAnyOrder setOf(
            listOf(1, 2),
            listOf(2, 1)
        )

        listOf(1, 2, 3).permutations().toSet() shouldContainExactlyInAnyOrder setOf(
            listOf(1, 2, 3),
            listOf(1, 3, 2),
            listOf(2, 1, 3),
            listOf(2, 3, 1),
            listOf(3, 1, 2),
            listOf(3, 2, 1),
        )

        listOf(1, 2, 3, 4).permutations().toSet() shouldContainExactlyInAnyOrder setOf(
            listOf(1, 2, 3, 4),
            listOf(1, 3, 2, 4),
            listOf(2, 1, 3, 4),
            listOf(2, 3, 1, 4),
            listOf(3, 1, 2, 4),
            listOf(3, 2, 1, 4),

            listOf(1, 2, 4, 3),
            listOf(1, 3, 4, 2),
            listOf(2, 1, 4, 3),
            listOf(2, 3, 4, 1),
            listOf(3, 1, 4, 2),
            listOf(3, 2, 4, 1),

            listOf(1, 4, 2, 3),
            listOf(1, 4, 3, 2),
            listOf(2, 4, 1, 3),
            listOf(2, 4, 3, 1),
            listOf(3, 4, 1, 2),
            listOf(3, 4, 2, 1),

            listOf(4, 1, 2, 3),
            listOf(4, 1, 3, 2),
            listOf(4, 2, 1, 3),
            listOf(4, 2, 3, 1),
            listOf(4, 3, 1, 2),
            listOf(4, 3, 2, 1),
        )
    }
}
