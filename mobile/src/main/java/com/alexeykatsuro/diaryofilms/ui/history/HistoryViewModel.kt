package com.alexeykatsuro.diaryofilms.ui.history

import com.alexeykatsuro.data.repositories.HistoryRepository
import com.alexeykatsuro.diaryofilms.base.BaseViewModel
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
) : BaseViewModel() {

    val allFilms = historyRepository.allFilms
}