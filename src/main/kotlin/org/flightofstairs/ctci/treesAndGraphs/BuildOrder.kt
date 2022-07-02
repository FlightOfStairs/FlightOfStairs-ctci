package org.flightofstairs.ctci.treesAndGraphs

// Problem 4.7
fun buildOrder(projects: Set<String>, dependencies: Set<Pair<String, String>>): List<String> {

    val unblocks = dependencies.groupBy({ it.first }) { it.second }
    val remainingRequirements = dependencies.groupBy({ it.second }) { it.first }
        .mapValues { (_, v) -> v.toMutableSet() }
        .toMutableMap()

    val unblockedProjects = projects.filterTo(mutableSetOf()) { remainingRequirements[it].isNullOrEmpty() }

    val result = mutableListOf<String>()

    while (unblockedProjects.isNotEmpty()) {
        val iterator = unblockedProjects.iterator()
        val currentProject = iterator.next()
        iterator.remove()

        result.add(currentProject)

        (unblocks[currentProject] ?: emptyList()).forEach { potentiallyUnblocked ->
            val remainingBlockers = remainingRequirements[potentiallyUnblocked]!!
            remainingBlockers.remove(currentProject)

            if (remainingBlockers.isEmpty()) {
                unblockedProjects.add(potentiallyUnblocked)
                remainingRequirements.remove(potentiallyUnblocked)
            }
        }
    }

    if (result.size != projects.size) throw NoValidBuildOrderException()

    return result
}

class NoValidBuildOrderException : Exception()
