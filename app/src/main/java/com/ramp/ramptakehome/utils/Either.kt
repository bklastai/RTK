package com.ramp.ramptakehome.utils

sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()

    inline fun fold(left: (L) -> Any, right: (R) -> Any): Any =
        when (this) {
            is Left -> left(value)
            is Right -> right(value)
        }

    fun <T> map(transform: (R) -> T): Either<L, T> = when (this) {
        is Left -> this
        is Right -> Right(transform(value))
    }

    fun valueToString(): String {
        return fold(
            { it.toString() },
            { it.toString() }
        ) as String
    }
}