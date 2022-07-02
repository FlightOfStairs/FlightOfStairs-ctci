package org.flightofstairs.ctci.linkedLists

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class LoopDetectionKtTest {

    @Test
    fun example() {
        val loop = Node("C", Node("D", Node("E", null)))
        loop.next!!.next!!.next = loop
        val root = Node("A", Node("B", loop))

        loopDetection(root)!!.item shouldBe "C"
    }

    @Test
    fun noLoop() {
        loopDetection("ABCDE".toList().toNode()) shouldBe null
    }

    @Test
    fun tightLoop() {
        val root = Node("A", null)
        root.next = root

        loopDetection(root)!!.item shouldBe "A"
    }
}
