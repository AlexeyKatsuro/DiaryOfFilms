package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.alexeykatsuro.data.dto.FilmRecord
import com.alexeykatsuro.data.repositories.HistoryRepository
import com.alexeykatsuro.data.util.Event
import com.alexeykatsuro.data.util.extensions.map
import com.alexeykatsuro.data.util.extensions.triggerEvent
import com.alexeykatsuro.diaryofilms.base.mvrx.DofMvRxViewModel
import com.alexeykatsuro.inputfromutil.createForm
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AdoptFilmViewModel @AssistedInject constructor(
    @Assisted initialState: AdoptFilmState,
    private val historyRepository: HistoryRepository
) : DofMvRxViewModel<AdoptFilmState>(initialState) {

    private val _onFilmSaved = MutableLiveData<Event<Unit>>()
    val onFilmSaved: LiveData<Event<Unit>>
        get() = _onFilmSaved

    fun adoptFilm(film: FilmRecord) {
        viewModelScope.launch {
            historyRepository.adoptFilm(film)
            _onFilmSaved.triggerEvent()
        }
    }

    fun updateState(reducer: AdoptFilmState.() -> AdoptFilmState){
        setState(reducer)
    }


    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: AdoptFilmState): AdoptFilmViewModel
    }

    companion object : MvRxViewModelFactory<AdoptFilmViewModel, AdoptFilmState> {
        override fun create(viewModelContext: ViewModelContext, state: AdoptFilmState): AdoptFilmViewModel? {
            val fragment: AdoptFilmFragment = (viewModelContext as FragmentViewModelContext).fragment()
            return fragment.viewModelFactory.create(state)
        }
    }

}

