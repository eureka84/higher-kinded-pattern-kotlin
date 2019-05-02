package com.eureka

interface Functor<F> {
    fun <A, B> map(f: (A) -> B, fa: Kind<F, A>): Kind<F, B>
}