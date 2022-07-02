package org.flightofstairs.ctci.linkedLists

import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import org.junit.jupiter.api.Test

internal class PartitionKtTest {

    @Test
    fun example() {
        testPartition(listOf(3, 5, 8, 5, 10, 2, 1), 5)
    }

    @Test
    fun allLess() {
        testPartition(listOf(1, 2, 3), 4)
    }

    @Test
    fun allMore() {
        testPartition(listOf(2, 3, 4), 1)
    }

}

private fun testPartition(inputList: List<Int>, partitionElement: Int) {
    val result = partition(inputList.toNode(), partitionElement).asSequence().toList()

    val totalItemsLessThanPartitionElement = inputList.count { it < partitionElement }

    result shouldContainExactlyInAnyOrder inputList

    result.subList(0, totalItemsLessThanPartitionElement).forAll {
        it < partitionElement
    }

    result.subList(totalItemsLessThanPartitionElement, result.size).forAll {
        it >= partitionElement
    }
}
