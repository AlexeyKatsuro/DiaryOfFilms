package com.alexeykatsuro.diaryofilms.ui.history

import androidx.lifecycle.viewModelScope
import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.alexeykatsuro.data.repositories.HistoryRepository
import com.alexeykatsuro.diaryofilms.base.mvrx.DofMvRxViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch

class HistoryViewModel @AssistedInject constructor(
    @Assisted initialState: HistoryState,
    private val historyRepository: HistoryRepository
) : DofMvRxViewModel<HistoryState>(initialState) {

    private val allFilmsObserveble = historyRepository.allFilmsObserveble()

    init {
        viewModelScope.launch {
            allFilmsObserveble.execute {
                copy(films = it() ?: emptyList())
            }
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: HistoryState): HistoryViewModel
    }

    companion object : MvRxViewModelFactory<HistoryViewModel, HistoryState> {
        override fun create(viewModelContext: ViewModelContext, state: HistoryState): HistoryViewModel? {
            val fragment: HistoryFragment = (viewModelContext as FragmentViewModelContext).fragment()
            return fragment.historyViewModelFactory.create(state)
        }
    }
}