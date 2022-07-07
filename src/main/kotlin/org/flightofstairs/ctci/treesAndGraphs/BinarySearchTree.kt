package org.flightofstairs.ctci.treesAndGraphs

import org.flightofstairs.ctci.treesAndGraphs.BstDirection.LEFT
import org.flightofstairs.ctci.treesAndGraphs.BstDirection.MATCH
import org.flightofstairs.ctci.treesAndGraphs.BstDirection.RIGHT

class BinarySearchTree<Key : Comparable<Key>, Value>(private val comparator: Comparator<Key> = naturalOrder()) {
    private var root: BTreeNode<Entry<Key, Value>>? = null

    operator fun set(key: Key, value: Value) {
        val possibleNewNode = BTreeNode(Entry(key, value), null, null)

        if (root == null) {
            root = possibleNewNode
        } else {
            val candidate = path(key, root).last()

            when (val direction = comparator.direction(key, candidate.item.key)) {
                MATCH -> candidate.item.value = value
                else  -> candidate[direction] = possibleNewNode
            }
        }
    }

    operator fun get(key: Key): Value? {
        val entry = path(key, root).lastOrNull()?.item ?: return null

        return if (comparator.direction(key, entry.key) == MATCH) entry.value else null
    }

    fun delete(key: Key): Value? {
        val path = path(key, root).toList()
        val candidate = path.lastOrNull() ?: return null

        if (comparator.direction(key, candidate.item.key) != MATCH) {
            return null
        }

        val nodePostDeletion = deleteNode(candidate)

        // I.e., deleting root
        if (root == candidate) {
            root = nodePostDeletion
        } else {
            val parent = path[path.size - 2]
            val direction = comparator.direction(candidate.item.key, parent.item.key)
            parent[direction] = nodePostDeletion
        }
        return candidate.item.value
    }

    private fun deleteNode(node: BTreeNode<Entry<Key, Value>>?): BTreeNode<Entry<Key, Value>>? {
        if (node == null) return null
        if (node.left == null) return node.right
        if (node.right == null) return node.left

        val pathToSuccessor = leftMostPath(node.right).toList()
        val successor = pathToSuccessor.last()

        val tmpItem = node.item
        node.item = successor.item
        successor.item = tmpItem

        if (successor == node.right) node.right = deleteNode(successor)
        else pathToSuccessor[pathToSuccessor.size - 2].left = deleteNode(successor)

        return node
    }

    private fun leftMostPath(node: BTreeNode<Entry<Key, Value>>?): Sequence<BTreeNode<Entry<Key, Value>>> = sequence {
        if (node != null) {
            yield(node)
            yieldAll(leftMostPath(node.left))
        }
    }

    private fun path(
        key: Key,
        node: BTreeNode<Entry<Key, Value>>?,
    ): Sequence<BTreeNode<Entry<Key, Value>>> = sequence {
        if (node !== null) {
            yield(node)

            when (val direction = comparator.direction(key, node.item.key)) {
                MATCH -> {}
                else -> yieldAll(path(key, node[direction]))
            }
        }
    }
}

private data class Entry<Key, Value>(val key: Key, var value: Value)

private enum class BstDirection {
    LEFT,
    MATCH,
    RIGHT
}

private fun <T> Comparator<T>.direction(a: T, b: T): BstDirection {
    val comparison = compare(a, b)
    return when {
        comparison < 0 -> LEFT
        comparison > 0 -> RIGHT
        else -> MATCH
    }
}

private operator fun <T> BTreeNode<T>.get(direction: BstDirection): BTreeNode<T>? {
    return when (direction) {
        LEFT -> left
        MATCH -> error("Not a valid direction to get node: MATCH")
        RIGHT -> right
    }
}

private operator fun <T> BTreeNode<T>.set(direction: BstDirection, child: BTreeNode<T>?) {
    when (direction) {
        LEFT -> left = child
        MATCH -> error("Not a valid direction to update node: MATCH")
        RIGHT -> right = child
    }
}
