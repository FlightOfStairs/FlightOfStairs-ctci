package org.flightofstairs.ctci.arraysAndStrings

// Problem 1.2
fun isPermutation(a: String, b: String) =
    a.length == b.length && a.groupingBy { it }.eachCount() == b.groupingBy { it }.eachCount()
