package org.flightofstairs.ctci.recusionAndDynamicProgramming

import org.flightofstairs.ctci.treesAndGraphs.Position


fun nQueens(count: Int) = (0 until count).toList().permutations().map {
    it.mapIndexed { row, col -> Position(row, col) }
}.filter { solution ->
    val posIntercepts = solution.map { (it.y - it.x) }.toSet()
    val negIntercepts = solution.map { (it.y + it.x) }.toSet()

    posIntercepts.size == count && negIntercepts.size == count
}

fun <T> List<T>.permutations(): Sequence<List<T>> = toMutableList().permutations(size)

// Heap's algorithm: https://en.wikipedia.org/wiki/Heap%27s_algorithm
private fun <T> MutableList<T>.permutations(k: Int): Sequence<List<T>> = sequence {
    if (k == 1) yield(this@permutations.toList()) // yield copy
    else {
        yieldAll(permutations(k - 1))

        for (i in (0 until k - 1)) {
            val swapIdx = if (k % 2 == 0) i else 0
            val tmp = this@permutations[swapIdx]
            this@permutations[swapIdx] = this@permutations[k - 1]
            this@permutations[k - 1] = tmp

            yieldAll(permutations(k - 1))
        }
    }
}
