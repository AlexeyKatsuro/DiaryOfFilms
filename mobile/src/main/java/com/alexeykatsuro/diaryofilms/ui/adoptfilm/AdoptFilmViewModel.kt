package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alexeykatsuro.data.dto.FilmRecord
import com.alexeykatsuro.data.repositories.HistoryRepository
import com.alexeykatsuro.data.util.Event
import com.alexeykatsuro.data.util.extensions.triggerEvent
import com.alexeykatsuro.diaryofilms.base.BaseViewModel
import com.alexeykatsuro.inputfromutil.createForm
import kotlinx.coroutines.launch
import javax.inject.Inject

class AdoptFilmViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
) : BaseViewModel() {

    val inputForm: AdoptInputForm = createForm(::AdoptInputForm) {
        onStateChanged {
            _onStateChanged.value = it
        }

    }

    private val _onStateChanged = MutableLiveData<AdoptInputState>()
    val onStateChanged: LiveData<AdoptInputState>
        get() = _onStateChanged

    private val _onFilmSaved = MutableLiveData<Event<Unit>>()
    val onFilmSaved: LiveData<Event<Unit>>
        get() = _onFilmSaved

    fun adoptFilm(film: FilmRecord) {
        launch {
            historyRepository.adoptFilm(film)
            _onFilmSaved.triggerEvent()
        }
    }
}