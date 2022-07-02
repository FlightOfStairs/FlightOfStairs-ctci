package org.flightofstairs.ctci.treesAndGraphs

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

private val testTree = BTreeNode(10,
    BTreeNode(5, null, BTreeNode(12, null, null)),
    BTreeNode(20,
        BTreeNode(3, BTreeNode(9, null, null), BTreeNode(18, null, null)),
        BTreeNode(7, null, null)
    )
)

internal class BinaryTreeTraversalKtTest {
    @Test
    fun preOrderTraversal() {
        testTree.preOrderTraversal().toList() shouldBe listOf(10, 5, 12, 20, 3, 9, 18, 7)
    }

    @Test
    fun inOrderTraversal() {
        testTree.inOrderTraversal().toList() shouldBe listOf(5, 12, 10, 9, 3, 18, 20, 7)
    }

    @Test
    fun postOrderTraversal() {
        testTree.postOrderTraversal().toList() shouldBe listOf(12, 5, 9, 18, 3, 7, 20, 10)
    }
}
