package org.flightofstairs.ctci.stacksAndQueues


private class BiStack<T> {
    private data class BiNode<T>(val item: T, var previous: BiNode<T>?, var next: BiNode<T>?)

    var size: Int = 0
        private set

    private var first: BiNode<T>? = null
    private var last: BiNode<T>? = null

    fun pushStart(item: T) {
        if (first == null) {
            assert(last == null)

            first = BiNode(item, null, null)
            last = first
        } else {
            val oldFirst = first
            first = BiNode(item, null, oldFirst)
            oldFirst!!.previous = first
        }
        size++
    }

    fun pushEnd(item: T) {
        if (last == null) {
            assert(first == null)

            last = BiNode(item, null, null)
            first = last
        } else {
            val oldLast = last
            last = BiNode(item, oldLast, null)
            oldLast!!.next = last
        }
        size++
    }

    fun popStart(): T {
        if (first == null) throw PopFromEmptyStackException()
        val oldFirst = first
        first = first!!.next
        first?.let {
            it.previous = null
        }

        size--

        return oldFirst!!.item
    }

    fun popEnd(): T {
        if (last == null) throw PopFromEmptyStackException()
        val oldLast = last
        last = last!!.previous
        last?.let {
            it.next = null
        }

        size--

        return oldLast!!.item
    }
}

class PopFromEmptyStackException : Exception()

class ReversableStack<T> {
    private val biStack = BiStack<T>()
    private var reversed = false

    val size: Int
        get() = biStack.size

    fun push(item: T) {
        when (reversed) {
            false -> biStack.pushStart(item)
            true -> biStack.pushEnd(item)
        }
    }

    fun pop(): T = when (reversed) {
        false -> biStack.popStart()
        true -> biStack.popEnd()
    }

    fun reverse() {
        reversed = !reversed
    }
}
