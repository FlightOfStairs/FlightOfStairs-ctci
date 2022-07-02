package org.flightofstairs.ctci.linkedLists

fun <T> List<T>.toNode() = asReversed().fold(null) { acc: Node<T>?, item -> Node(item, acc) }

fun <T> Node<T>?.asSequence() = object : Sequence<T> {
    override fun iterator(): Iterator<T> {
        var current = this@asSequence

        return object : Iterator<T> {
            override fun hasNext() = current != null
            override fun next(): T {
                if (!hasNext()) {
                    throw NoSuchElementException()
                }

                val item = current!!.item
                current = current!!.next
                return item
            }
        }
    }
}

fun <T> Node<T>?.toList() = asSequence().toList()
