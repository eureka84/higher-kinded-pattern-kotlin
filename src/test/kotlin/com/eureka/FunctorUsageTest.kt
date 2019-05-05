package com.eureka

import com.eureka.examples.ArithmeticWithEffects
import com.eureka.lib.*
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class FunctorUsageTest {

    @Test
    fun `arithmetic with option and ints`() {
        val arithmetic = object : ArithmeticWithEffects<ForOption, Int> {
            override val f: Functor<ForOption> = Option.functor
            override val n: Num<Int> = Num.int()
        }

        val result = arithmetic.square(Option.just(3)).fix()

        assertThat(result, equalTo(Option.just(9)))
    }

    @Test
    fun `arithmetic with Try and double`() {
        val arithmetic = object : ArithmeticWithEffects<ForTry, Double> {
            override val f: Functor<ForTry> = Try.functor
            override val n: Num<Double> = Num.double()
        }

        val result = arithmetic.square(Try.attempt { 3.0 }).fix()

        assertThat(result, equalTo(Try.attempt { 9.0 }))
    }

}

