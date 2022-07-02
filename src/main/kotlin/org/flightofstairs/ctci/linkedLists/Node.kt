package org.flightofstairs.ctci.linkedLists

data class Node<T>(val item: T, var next: Node<T>?)

fun <T> Node<T>?.reverse(): Node<T>? = reverse(null)

private fun <T> Node<T>?.reverse(previous: Node<T>?): Node<T>? =
    if (this == null) previous else next.reverse(Node(item, previous))

fun <T> Node<T>?.reverseIter(): Node<T>? {
    var current = this
    var last: Node<T>? = null

    while(current != null) {
        last = Node(current.item, last)
        current = current.next
    }

    return last
}

// Warning: Other reverse methods do not mutate original list. This one does.
fun <T> Node<T>?.reverseInPlace(): Node<T>? {
    var current = this
    var previous: Node<T>? = null

    while(current != null) {
        val next = current.next
        current.next = previous
        previous = current
        current = next
    }

    return previous
}
