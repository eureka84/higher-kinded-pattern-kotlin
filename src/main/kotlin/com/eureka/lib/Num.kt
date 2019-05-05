package com.eureka.lib

interface Num<N> {

    operator fun N.plus(other: N): N
    operator fun N.minus(other: N): N
    operator fun N.times(other: N): N
    operator fun N.div(other: N): N

    companion object {
        fun int(): Num<Int> = object : Num<Int> {
            override fun Int.plus(other: Int): Int = this + other
            override fun Int.minus(other: Int): Int = this - other
            override fun Int.times(other: Int): Int = this * other
            override fun Int.div(other: Int): Int = this / other
        }

        fun long(): Num<Long> = object : Num<Long> {
            override fun Long.plus(other: Long): Long = this + other
            override fun Long.minus(other: Long): Long = this - other
            override fun Long.times(other: Long): Long = this * other
            override fun Long.div(other: Long): Long = this / other
        }

        fun float(): Num<Float> = object : Num<Float> {
            override fun Float.plus(other: Float): Float = this + other
            override fun Float.minus(other: Float): Float = this - other
            override fun Float.times(other: Float): Float = this * other
            override fun Float.div(other: Float): Float = this / other
        }

        fun double(): Num<Double> = object : Num<Double> {
            override fun Double.plus(other: Double): Double = this + other
            override fun Double.minus(other: Double): Double = this - other
            override fun Double.times(other: Double): Double = this * other
            override fun Double.div(other: Double): Double = this / other
        }
    }
}
