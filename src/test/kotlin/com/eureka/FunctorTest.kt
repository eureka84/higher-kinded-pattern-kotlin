package com.eureka

import com.eureka.lib.Option
import com.eureka.lib.Try
import com.eureka.lib.fix
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class FunctorTest {

    private val increment: (Int) -> Int = { i -> i + 1 }


    @Test
    fun `mapping over an non empty option`() {
        val nonEmptyOption: Option<Int> = Option.just(3)

        val actual = Option.functor.map(increment, nonEmptyOption).fix()

        assertThat(actual, equalTo(Option.just(4)))
    }

    @Test
    fun `mapping over an empty option`() {
        val empty = Option.none<Int>()

        val actual = Option.functor.map(increment, empty).fix()

        assertThat(actual, equalTo(empty))
    }

    @Test
    fun `mapping over a success try`() {
        val success = Try.attempt { 6 }

        val actual = Try.functor.map(increment, success).fix()

        assertThat(actual, equalTo(Try.attempt { 7 }))
    }

    @Test
    fun `mapping over a failure try`() {
        val failure: Try<Int> = Try.attempt { throw RuntimeException("BOOM!") }

        val actual = Try.functor.map(increment, failure).fix()

        assertThat(actual, equalTo(failure))
    }
}
