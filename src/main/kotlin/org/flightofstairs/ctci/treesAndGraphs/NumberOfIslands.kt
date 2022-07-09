package org.flightofstairs.ctci.treesAndGraphs

data class Position(val x: Int, val y: Int) {

    // Supports diagonals
    fun adjacentPositions() = (-1..1).flatMap { dx ->
        (-1..1).map { dy ->
            Position(x + dx, y + dy)
        }
    }.filter { it != this }
}

private fun positionExists(position: Position, matrix: List<List<Int>>) =
    matrix.indices.contains(position.x) && matrix[position.x].indices.contains(position.y)

private fun positionsPrioritizingIslands(matrix: List<List<Int>>): Sequence<Position> = sequence {
    val visited = mutableSetOf<Position>()
    val deque = ArrayDeque<Position>()

    val first = Position(0, 0)

    deque.add(first)
    visited.add(first)

    while (!deque.isEmpty()) {
        val current = deque.removeLast()
        yield(current)

        val subsequent = current.adjacentPositions()
            .filter { positionExists(it, matrix) }
            .filter { !visited.contains(it) }

        for (position in subsequent) {
            visited.add(position)
            if (matrix[position] == 0) deque.addFirst(position) else deque.addLast(position)
        }
    }
}

fun numberOfIslands(matrix: List<List<Int>>): Int {
    val initialIslandCount = matrix[0][0]

    return initialIslandCount + positionsPrioritizingIslands(matrix)
        .map { matrix[it] }
        .windowed(2)
        .count { it[0] != 1 && it[1] == 1 }
}

private operator fun List<List<Int>>.get(position: Position): Int {
    check(positionExists(position, this))

    return this[position.x][position.y]
}
