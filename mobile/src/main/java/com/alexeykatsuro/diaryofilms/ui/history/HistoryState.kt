package com.alexeykatsuro.diaryofilms.ui.history

import com.airbnb.mvrx.MvRxState
import com.alexeykatsuro.data.dto.FilmRecord

data class HistoryState(
    val films: List<FilmRecord> = emptyList()
): MvRxState