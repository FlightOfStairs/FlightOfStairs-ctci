package org.flightofstairs.ctci.stacksAndQueues

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class ReversableStackTest {

    @Test
    fun popEmpty() {
        val stack = ReversableStack<Int>()
        stack.size shouldBe 0

        shouldThrow<PopFromEmptyStackException> {
            stack.pop()
        }
    }

    @Test
    fun firstInLastOut() {
        val stack = ReversableStack<Int>()
        stack.size shouldBe 0
        stack.push(1)
        stack.size shouldBe 1
        stack.push(2)
        stack.size shouldBe 2
        stack.push(3)
        stack.size shouldBe 3

        stack.pop() shouldBe 3
        stack.size shouldBe 2
        stack.pop() shouldBe 2
        stack.size shouldBe 1
        stack.pop() shouldBe 1
        stack.size shouldBe 0
    }

    @Test
    fun reversePops() {
        val stack = ReversableStack<Int>()
        stack.push(1)
        stack.push(2)
        stack.push(3)

        stack.reverse()

        stack.pop() shouldBe 1
        stack.pop() shouldBe 2
        stack.pop() shouldBe 3

        shouldThrow<PopFromEmptyStackException> {
            stack.pop()
        }
    }

    @Test
    fun reversePushAndPop() {
        val stack = ReversableStack<Int>()
        stack.push(1)
        stack.push(2)

        stack.reverse()

        stack.push(3)
        stack.push(4)

        stack.pop() shouldBe 4
        stack.pop() shouldBe 3
        stack.pop() shouldBe 1
        stack.pop() shouldBe 2

        shouldThrow<PopFromEmptyStackException> {
            stack.pop()
        }
    }
}
