package org.flightofstairs.ctci.treesAndGraphs

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BinarySearchTreeTest {

    @Test
    fun emptyBst() {
        val bst = BinarySearchTree<String, String>()
        bst["foo"].shouldBeNull()
    }

    @Test
    fun setAndReplacement() {
        val bst = BinarySearchTree<String, String>()
        bst["foo"] = "bar"
        bst["foo"] shouldBe "bar"
        bst["foo"] = "baz"
        bst["foo"] shouldBe "baz"
    }

    @Test
    fun setMany() {
        val bst = BinarySearchTree<String, Int>()

        (0 until 1000).shuffled().forEach {
            bst["key-${it.toString().padStart(3, '0')}"] = it
        }

        (0 until 1000).forEach {
            bst["key-${it.toString().padStart(3, '0')}"] shouldBe it
        }
    }

    @Test
    fun deleteEmpty() {
        val bst = BinarySearchTree<String, String>()
        bst.delete("foo") shouldBe null
    }

    @Test
    fun deleteSingle() {
        val bst = BinarySearchTree<String, String>()
        bst["foo"] = "bar"
        bst.delete("foo") shouldBe "bar"
        bst["foo"] shouldBe null
    }

    @Test
    fun deleteMany() {
        val keys = (0 until 1000).map { it.toString().padStart(5, '0') }.shuffled()

        val bst = BinarySearchTree<String, String>()
        for (key in keys) {
            bst[key] = "foo"
        }

        val deletedKeys = keys.shuffled().take(100).toSet()

        for (key in deletedKeys) {
            bst.delete(key) shouldBe "foo"
        }

        for (key in keys) {
            val expected = if (deletedKeys.contains(key)) null else "foo"
            bst[key] shouldBe expected
        }
    }
}
