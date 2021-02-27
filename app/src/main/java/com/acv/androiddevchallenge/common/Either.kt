package com.acv.androiddevchallenge.common


sealed class Either<out E, out A>
data class Left<out E>(val e: E) : Either<E, Nothing>()
data class Right<out A>(val a: A) : Either<Nothing, A>()

suspend fun <E, A, B> Either<E, A>.fold(ifLeft: suspend (E) -> B, ifRight: suspend (A) -> B): B =
    when (this) {
        is Left -> ifLeft(e)
        is Right -> ifRight(a)
    }

suspend fun <A, B, C> Either<A, B>.map(f: suspend (B) -> C): Either<A, C> =
    flatMap { Right(f(it)) }

suspend fun <A, B, C> Either<A, B>.flatMap(f: suspend (B) -> Either<A, C>): Either<A, C> =
    when (this) {
        is Right -> f(a)
        is Left -> Left(e)
    }
