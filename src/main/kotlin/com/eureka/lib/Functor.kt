package com.eureka.lib

interface Functor<F> {
    fun <A, B> map(f: (A) -> B, fa: Kind<F, A>): Kind<F, B>
    fun <A, B> Kind<F, A>.map(f: (A) -> B): Kind<F, B> = map(f, this)
}