package org.flightofstairs.ctci.linkedLists

// Problem 2.4
fun <T: Comparable<T>> partition(root: Node<T>?, k: T): Node<T>? {
    if (root == null) return root

    var ref = root

    var lowerHead: Node<T>? = null
    var lowerLast: Node<T>? = null
    var upper: Node<T>? = null

    while (ref != null) {
        if (ref.item < k) {
            lowerHead = Node(ref.item, lowerHead)
            if (lowerLast == null) lowerLast = lowerHead
        } else {
            upper = Node(ref.item, upper)
        }

        ref = ref.next
    }

    if (lowerLast == null) return upper
    lowerLast.next = upper
    return lowerHead
}
