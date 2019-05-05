package com.eureka.lib

class ForTry {
    companion object
}
typealias TryOf<A> = Kind<ForTry, A>
fun <A> TryOf<A>.fix() = this as Try<A>

sealed class Try<out A> : TryOf<A> {

    fun <B> map(f: (A) -> B): Try<B> = when (this) {
        is Success -> Success(f(value))
        is Failure -> Failure(throwable)
    }

    companion object {
        val functor: Functor<ForTry> = object : Functor<ForTry> {
            override fun <A, B> map(f: (A) -> B, fa: Kind<ForTry, A>): Kind<ForTry, B> = fa.fix().map(f)
        }

        fun <A> attempt(supplier: () -> A): Try<A> =
            try {
                Success(supplier())
            } catch (t: Throwable) {
                Failure(t)
            }
    }

}

data class Success<A>(val value: A) : Try<A>()
data class Failure(val throwable: Throwable) : Try<Nothing>()