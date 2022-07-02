package org.flightofstairs.ctci.linkedLists

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class IntersectionKtTest {

    @Test
    fun example() {
        val common = Node(7, Node(2, Node(1, null)))
        val left = Node(3, Node(1, Node(5, Node(9, common))))
        val right = Node(4, Node(6, common))

        intersection(left, right) shouldBe common
    }

    @Test
    fun noIntersection() {
        val left = listOf(1, 2, 3).toNode()
        val right = listOf(4, 5).toNode()

        intersection(left, right) shouldBe null
    }
}
