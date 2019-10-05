package com.alexeykatsuro.data.util.extensions

fun <T> List<T>.copy(index: Int, item: T): List<T> =
    toMutableList().apply { set(index, item) }