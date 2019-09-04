package com.alexeykatsuro.diaryofilms.ui.history

import com.alexeykatsuro.data.repositories.HistoryRepository
import com.alexeykatsuro.diaryofilms.base.DofViewModel
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
) : DofViewModel() {

    val allFilms = historyRepository.allFilms
}