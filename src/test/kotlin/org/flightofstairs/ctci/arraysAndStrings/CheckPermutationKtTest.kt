package org.flightofstairs.ctci.arraysAndStrings

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CheckPermutationKtTest {

    @Test
    fun example() {
        isPermutation("tacocat", "atcocta") shouldBe true
    }

    @Test
    fun uneven() {
        isPermutation("tacocat", "atcoc") shouldBe false
    }

    @Test
    fun notPermutation() {
        isPermutation("tacocat", "tacoctt") shouldBe false
    }

    @Test
    fun empty() {
        isPermutation("", "") shouldBe true
    }
}
