package org.flightofstairs.ctci.linkedLists

// Problem 2.5
fun sumLists(left: Node<Int>?, right: Node<Int>?): Node<Int>? {
    var leftRef = left
    var rightRef = right
    var carry = 0

    var ret: Node<Int>? = null
    var last: Node<Int>? = null

    while(leftRef != null || rightRef != null || carry != 0) {
        val sum = (leftRef?.item ?: 0) + (rightRef?.item ?: 0) + carry

        val newLast = Node(sum % 10, null)
        if (last == null) {
            ret = newLast
        } else {
            last.next = newLast
        }
        last = newLast

        leftRef = leftRef?.next
        rightRef = rightRef?.next
        carry = sum / 10
    }

    return ret
}

fun sumListsReversed(left: Node<Int>?, right: Node<Int>?) =
    sumLists(left.reverse(), right.reverse()).reverse()
