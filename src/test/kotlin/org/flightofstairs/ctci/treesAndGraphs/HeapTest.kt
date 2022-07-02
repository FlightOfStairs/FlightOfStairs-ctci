package org.flightofstairs.ctci.treesAndGraphs

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class HeapTest {

    @Test
    fun throwsOnRemoveFromEmpty() {
        val heap = Heap<Int>()
        heap.size shouldBe 0

        shouldThrow<RetrieveFromEmptyHeapException> {
            heap.remove()
        }
    }

    @Test
    fun sortsValues() {
        val heap = Heap<Int>()

        val inserts = listOf(1, 5, 6, 9, 11, 8, 15, 21, 17, 3)

        inserts.forEach {
            heap.insert(it)
        }

        val removedItems = mutableListOf<Int>()
        while(heap.size > 0) {
            removedItems.add(heap.remove())
        }

        removedItems shouldBe inserts.sorted()
    }

    @Test
    fun sortsValuesDescending() {
        val heap = Heap<Int>(naturalOrder<Int>().reversed())

        val inserts = listOf(1, 5, 6, 9, 11, 8, 15, 21, 17, 3)

        inserts.forEach {
            heap.insert(it)
        }

        val removedItems = mutableListOf<Int>()
        while(heap.size > 0) {
            removedItems.add(heap.remove())
        }

        removedItems shouldBe inserts.sorted().reversed()
    }
}
