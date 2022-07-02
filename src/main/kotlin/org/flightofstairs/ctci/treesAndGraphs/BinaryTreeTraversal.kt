package org.flightofstairs.ctci.treesAndGraphs

data class BTreeNode<T>(val item: T, val left: BTreeNode<T>?, val right: BTreeNode<T>?)

fun <T> BTreeNode<T>.preOrderTraversal(): Sequence<T> =
    sequenceOf(item) + (left?.preOrderTraversal() ?: emptySequence()) + (right?.preOrderTraversal() ?: emptySequence())

fun <T> BTreeNode<T>.inOrderTraversal(): Sequence<T> =
     (left?.inOrderTraversal() ?: emptySequence()) + sequenceOf(item) + (right?.inOrderTraversal() ?: emptySequence())

fun <T> BTreeNode<T>.postOrderTraversal(): Sequence<T> =
    (left?.postOrderTraversal() ?: emptySequence()) +
        (right?.postOrderTraversal() ?: emptySequence()) +
        sequenceOf(item)
