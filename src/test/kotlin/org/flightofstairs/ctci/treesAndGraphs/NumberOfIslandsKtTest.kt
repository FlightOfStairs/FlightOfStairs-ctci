package org.flightofstairs.ctci.treesAndGraphs

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class NumberOfIslandsKtTest {

    @Test
    fun example() {
        // Example copied from: https://www.techiedelight.com/count-the-number-of-islands/

        val matrix = """
            1010001111
            0010101000
            1111001000
            1001010000
            1111000111
            0101001111
            0000011100
            0001001110
            1010100100
            1111000111
        """.trimIndent().lines().map { it.chunked(1).map { char -> char.toInt() } }

        numberOfIslands(matrix) shouldBe 5
    }
}
