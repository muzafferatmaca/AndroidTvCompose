package com.muzafferatmaca.androidtvcompose.util

/**
 * Created by Muzaffer Atmaca on 7.06.2024 at 23:56
 */


/**
 * Option class represents a product of a process that can have no value.
 */
sealed class Option<out T> {
    data object None : Option<Nothing>()
    data class Some<T>(val value: T) : Option<T>()
}

fun <T> Option<T>.unwrapOr(defaultValue: T): T {
    return (this as? Option.Some)?.value ?: defaultValue
}