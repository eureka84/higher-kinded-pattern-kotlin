package com.eureka.examples

import com.eureka.lib.Functor
import com.eureka.lib.Kind
import com.eureka.lib.Num

interface ArithmeticWithEffects<F, N> {

    val f: Functor<F>
    val n: Num<N>

    fun square(wrappedNumber: Kind<F, N>): Kind<F, N> =
        f.run {
            n.run {
                wrappedNumber.map { number -> number * number }
            }
        }

}