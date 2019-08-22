package com.alexeykatsuro.diaryofilms.ui.history

import com.alexeykatsuro.diaryofilms.base.BaseViewModel
import com.alexeykatsuro.diaryofilms.data.repositories.HistoryRepository
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
) : BaseViewModel() {

    val allFilms = historyRepository.allFilms
}