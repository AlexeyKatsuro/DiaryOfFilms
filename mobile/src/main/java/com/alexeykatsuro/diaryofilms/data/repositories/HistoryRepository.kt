package com.alexeykatsuro.diaryofilms.data.repositories

import com.alexeykatsuro.diaryofilms.data.dto.FilmRecord
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val historyLocaleDataSource: HistoryLocaleDataSource
) {
    val allFilms = historyLocaleDataSource.allFilms()

    suspend fun adoptFilm(film : FilmRecord){
        historyLocaleDataSource.insert(film)
    }
}