package com.eureka

import com.eureka.examples.ArithmeticWithEffects
import com.eureka.lib.*
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class FunctorUsageTest {

    @Test
    fun `arithmetic with option and ints`() {
        val program = object : ArithmeticWithEffects<ForOption, Int> {
            override val f: Functor<ForOption> = Option.functor
            override val n: Num<Int> = Num.int
        }

        val result: Option<Int> = program.square(Option.just(3)).fix()

        assertThat(result, equalTo(Option.just(9)))
    }

    @Test
    fun `arithmetic with Try and double`() {
        val program = object : ArithmeticWithEffects<ForTry, Double> {
            override val f: Functor<ForTry> = Try.functor
            override val n: Num<Double> = Num.double
        }

        val result = program.square(Try.attempt { 3.0 }).fix()

        assertThat(result, equalTo(Try.attempt { 9.0 }))
    }

    @Test
    fun `arithmetic on failing try`() {
        val program = object : ArithmeticWithEffects<ForTry, Double> {
            override val f: Functor<ForTry> = Try.functor
            override val n: Num<Double> = Num.double
        }

        val runtimeException = RuntimeException("BOOM!")

        val result = program.square(Try.attempt { throw runtimeException }).fix()

        val expected: Try<Double> = Failure(runtimeException)

        assertThat(result, equalTo(expected))
    }

}

