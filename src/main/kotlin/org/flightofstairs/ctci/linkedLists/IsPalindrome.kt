package org.flightofstairs.ctci.linkedLists

// Leetcode: https://leetcode.com/problems/palindrome-linked-list/
fun <T> isPalindrome(head: Node<T>?): Boolean {
    if (head?.next == null) return true

    var current = middleNode(head)
    var previous: Node<T>? = null

    while (current != null) {
        val next = current.next
        current.next = previous
        previous = current
        current = next
    }

    var left = head
    var right = previous

    while (left != right) {
        if (left?.item != right?.item) return false
        left = left?.next
        right = right?.next

        if (left != null && right == null) return true // This occurs when list length is even
    }
    return true
}

// From previous question
private fun <T> middleNode(head: Node<T>?): Node<T>? {
    var fast = head
    var slow = head

    while (fast?.next != null) {
        fast = fast.next?.next
        slow = slow?.next
    }

    return slow
}
