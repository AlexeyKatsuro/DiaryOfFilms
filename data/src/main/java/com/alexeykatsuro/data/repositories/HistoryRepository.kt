package com.alexeykatsuro.data.repositories

import com.alexeykatsuro.data.dto.FilmRecord
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val historyLocaleDataSource: HistoryLocaleDataSource
) {
    val allFilms = historyLocaleDataSource.allFilms()

    suspend fun adoptFilm(film: FilmRecord) {
        historyLocaleDataSource.insert(film)
    }
}