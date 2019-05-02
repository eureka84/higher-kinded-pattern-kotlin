package com.eureka

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class FunctorTest {

    val increment: (Int) -> Int = { i -> i + 1 }


    @Test
    fun `mapping over an non empty option`() {
        val nonEmptyOption: Option<Int> = Option.just(3)

        val actual = Option.functor<Int>().map(increment, nonEmptyOption).fix()

        assertThat(actual, equalTo(Option.just(4)))
    }

    @Test
    fun `mapping over an empty option`() {
        val empty = Option.none<Int>()

        val actual = Option.functor<Int>().map(increment, empty).fix()

        assertThat(actual, equalTo(empty))
    }
}
