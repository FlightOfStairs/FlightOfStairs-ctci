package org.flightofstairs.ctci.linkedLists

// Problem 2.8
fun <T> loopDetection(root: Node<T>?): Node<T>? {
    if (root == null) return null

    var tortoise = root
    var hare = root

    do {
        tortoise = tortoise?.next
        hare = hare?.next?.next
    } while (tortoise != hare && hare != null)

    if (hare == null) return null

    // I would not have independently arrived at this solution.
    // Instead, when tortoise/hare match, I'd have reversed their paths (need to track path, O(n) space), then used same
    // approach as intersection problem.
    tortoise = root

    while (tortoise != hare) {
        tortoise = tortoise?.next
        hare = hare?.next
    }

    return hare
}
