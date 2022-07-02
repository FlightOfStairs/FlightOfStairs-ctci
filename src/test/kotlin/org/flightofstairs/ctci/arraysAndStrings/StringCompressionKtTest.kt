package org.flightofstairs.ctci.arraysAndStrings

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class StringCompressionKtTest {

    @Test
    fun example() {
        stringCompression("aabcccccaaa") shouldBe "a2b1c5a3"
    }

    @Test
    fun empty() {
        stringCompression("") shouldBe ""
    }

    @Test
    fun tooShort() {
        stringCompression("a") shouldBe "a"
        stringCompression("abca") shouldBe "abca"
    }
}
