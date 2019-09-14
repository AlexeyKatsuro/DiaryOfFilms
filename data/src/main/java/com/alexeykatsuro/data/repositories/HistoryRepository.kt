package com.alexeykatsuro.data.repositories

import com.alexeykatsuro.data.dto.FilmRecord
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val historyLocaleDataSource: HistoryLocaleDataSource
) {
    fun allFilmsObserveble() = historyLocaleDataSource.allFilmsObservable()

    suspend fun adoptFilm(film: FilmRecord) {
        historyLocaleDataSource.insert(film)
    }
}