package org.flightofstairs.ctci.linkedLists

// Problem 2.7
fun <T> intersection(left: Node<T>?, right: Node<T>?): Node<T>? {
    if (left == null || right == null) return null

    var leftStack: Node<Node<T>?>? = Node(left, null)
    var rightStack: Node<Node<T>?>? = Node(right, null)

    while(leftStack!!.item?.next != null) leftStack = Node(leftStack.item?.next, leftStack)
    while(rightStack!!.item?.next != null) rightStack = Node(rightStack.item?.next, rightStack)

    if (leftStack.item != rightStack.item) return null

    var ret = leftStack.item

    while(leftStack != null && rightStack != null && leftStack.item == rightStack.item) {
        ret = leftStack.item

        leftStack = leftStack.next
        rightStack = rightStack.next
    }

    return ret
}
