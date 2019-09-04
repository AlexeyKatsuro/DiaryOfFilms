package com.alexeykatsuro.data.repositories

import com.alexeykatsuro.data.mappers.Mapper

interface DataSource {

    suspend fun <F, T> withSuspendMap(mapper: Mapper<F, T>, block: suspend () -> F): T {
        return mapper.map(block())
    }

    fun <F, T> withMap(mapper: Mapper<F, T>, block: () -> F): T {
        return mapper.map(block())
    }

}