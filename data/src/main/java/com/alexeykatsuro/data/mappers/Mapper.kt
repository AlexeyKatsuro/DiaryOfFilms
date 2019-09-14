package com.alexeykatsuro.data.mappers

typealias MapFunction<F, T> = (from: F) -> T

interface Mapper<F, T> {
    fun map(from: F): T
}

interface ListMapper<F, T> : Mapper<List<F>, List<T>>

fun <F, T> Mapper<F, T>.toListMapper() = object : ListMapper<F, T> {
    override fun map(from: List<F>): List<T> = from.map {
        this@toListMapper.map(it)
    }
}

abstract class IndexedMapper<F, T> : ListMapper<F, T> {

    abstract fun mapIndexed(index: Int, from: F): T

    final override fun map(from: List<F>): List<T> = from.mapIndexed(::mapIndexed)
}