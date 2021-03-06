package com.eureka.lib

class ForOption
typealias OptionOf<A> = Kind<ForOption, A>
fun <A> OptionOf<A>.fix(): Option<A> = this as Option<A>

sealed class Option<out A> : Kind<ForOption, A> {

    fun <B> map(f: (A) -> B): Option<B> = when (this) {
        is Just -> Just(f(this.value))
        is None -> None
    }

    companion object {
        val functor: Functor<ForOption> = object : Functor<ForOption> {
            override fun <A, B> map(f: (A) -> B, fa: Kind<ForOption, A>): Kind<ForOption, B> = fa.fix().map(f)
        }
        fun <A> just(value: A): Option<A> = Just(value)
        fun <A> none(): Option<A> = None
    }

}

data class Just<A>(val value: A) : Option<A>()
object None : Option<Nothing>()