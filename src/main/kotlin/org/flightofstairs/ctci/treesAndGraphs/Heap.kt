package org.flightofstairs.ctci.treesAndGraphs

// The main thing I learned from this is that it's way easier to implement a heap using an array or list than a
// classic tree. A lot of effort here is spent on pathing to the last element, which is trivial with array.
class Heap<T : Comparable<T>>(private val comparator: Comparator<T> = naturalOrder()) {

    private var root: BTreeNode<T>? = null
    var size = 0
        private set

    fun peek(): T = root?.item ?: throw RetrieveFromEmptyHeapException()

    fun insert(item: T) {
        size++

        val path = pathFromIndex(size)

        if (path.isEmpty()) {
            assert(root == null)

            root = BTreeNode(item, null, null)
        } else {
            root!!.insertAndHeapify(path, item, comparator)
        }
    }

    fun remove(): T {
        val item = peek()

        val path = pathFromIndex(size)

        if (path.isEmpty()) {
            assert(size == 1)

            root = null
        } else {
            root!!.item = root!!.deleteAt(path)

            root!!.heapifyDown(comparator)
        }

        size--
        return item
    }
}

class RetrieveFromEmptyHeapException : Exception()

private enum class Direction {
    LEFT, RIGHT
}

private fun pathFromIndex(i: Int) = i.toString(2).map { if (it == '0') Direction.LEFT else Direction.RIGHT }.drop(1)

private fun <T> BTreeNode<T>.insertAndHeapify(path: List<Direction>, newItem: T, comparator: Comparator<T>) {
    check(path.isNotEmpty())

    val direction = path.first()

    if (path.size == 1) {
        val newNode = BTreeNode(newItem, null, null)

        when (direction) {
            Direction.LEFT -> left = newNode
            Direction.RIGHT -> right = newNode
        }
    } else {
        when (direction) {
            Direction.LEFT -> left!!.insertAndHeapify(path.drop(1), newItem, comparator)
            Direction.RIGHT -> right!!.insertAndHeapify(path.drop(1), newItem, comparator)
        }
    }

    val heapifyNode = when (direction) {
        Direction.LEFT -> left!!
        Direction.RIGHT -> right!!
    }

    if (comparator.compare(this.item, heapifyNode.item) > 0) {
        val oldItem = this.item
        this.item = heapifyNode.item
        heapifyNode.item = oldItem
    }
}

private fun <T> BTreeNode<T>.deleteAt(path: List<Direction>): T {
    check(path.isNotEmpty())

    val direction = path.first()

    return if (path.size == 1) {
        when(direction) {
            Direction.LEFT -> left!!.item.also { left = null }
            Direction.RIGHT -> right!!.item.also { right = null }
        }
    } else {
        when(direction) {
            Direction.LEFT -> left!!.deleteAt(path.drop(1))
            Direction.RIGHT -> right!!.deleteAt(path.drop(1))
        }
    }
}

private fun <T> BTreeNode<T>.heapifyDown(comparator: Comparator<T>) {
    if (left == null) return

    val smallestChild = if (right == null || comparator.compare(left!!.item, right!!.item) <= 0 ) left else right

    if (comparator.compare(this.item, smallestChild!!.item) > 0) {
        val oldItem = this.item
        this.item = smallestChild.item
        smallestChild.item = oldItem
        smallestChild.heapifyDown(comparator)
    }
}
