package org.flightofstairs.ctci.linkedLists

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.jupiter.api.Test

internal class IsPalindromeKtTest {

    @Test
    fun example1() {
        isPalindrome(listOf(1, 2, 2, 1).toNode()).shouldBeTrue()
    }

    @Test
    fun oddNumber() {
        isPalindrome(listOf(1, 2, 3, 4, 3, 2, 1).toNode()).shouldBeTrue()
    }

    @Test
    fun example2() {
        isPalindrome(listOf(1, 2).toNode()).shouldBeFalse()
    }

    @Test
    fun empty() {
        isPalindrome<Int>(null).shouldBeTrue()
    }

    @Test
    fun single() {
        isPalindrome(listOf(1).toNode()).shouldBeTrue()
    }
}
