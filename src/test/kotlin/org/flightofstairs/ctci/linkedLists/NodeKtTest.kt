package org.flightofstairs.ctci.linkedLists

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class NodeKtTest {

    @Test
    fun reverseEmpty() {
        emptyList<Int>().toNode().reverse().toList() shouldBe emptyList()
        emptyList<Int>().toNode().reverseIter().toList() shouldBe emptyList()
        emptyList<Int>().toNode().reverseInPlace().toList() shouldBe emptyList()
    }

    @Test
    fun reverseSingle() {
        listOf(1).toNode().reverse().toList() shouldBe listOf(1)
        listOf(1).toNode().reverseIter().toList() shouldBe listOf(1)
        listOf(1).toNode().reverseInPlace().toList() shouldBe listOf(1)
    }

    @Test
    fun reverseMany() {
        listOf(1, 2, 3).toNode().reverse().toList() shouldBe listOf(3, 2, 1)
        listOf(1, 2, 3).toNode().reverseIter().toList() shouldBe listOf(3, 2, 1)
        listOf(1, 2, 3).toNode().reverseInPlace().toList() shouldBe listOf(3, 2, 1)
    }
}
