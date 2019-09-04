package com.alexeykatsuro.diaryofilms.ui.history

import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.alexeykatsuro.data.dto.FilmRecord
import com.alexeykatsuro.data.repositories.HistoryRepository
import com.alexeykatsuro.diaryofilms.base.mvrx.DofMvRxViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class HistoryViewModel @AssistedInject constructor(
    @Assisted initialState: HistoryState,
    private val historyRepository: HistoryRepository
) : DofMvRxViewModel<HistoryState>(initialState) {

    //val allFilms = historyRepository.allFilms
    //TODO Load from DB
    fun setFilmHistory(list: List<FilmRecord>) = setState {
        copy(films = list)
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: HistoryState): HistoryViewModel
    }

    companion object : MvRxViewModelFactory<HistoryViewModel, HistoryState>{
        override fun create(viewModelContext: ViewModelContext, state: HistoryState): HistoryViewModel? {
            val fragment: HistoryFragment =(viewModelContext as FragmentViewModelContext).fragment()
            return fragment.historyViewModelFactory.create(state)
        }
    }
}