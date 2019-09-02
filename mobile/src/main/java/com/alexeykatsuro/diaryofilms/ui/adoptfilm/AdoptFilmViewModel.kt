package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alexeykatsuro.diaryofilms.base.BaseViewModel
import com.alexeykatsuro.diaryofilms.data.dto.FilmRecord
import com.alexeykatsuro.diaryofilms.data.repositories.HistoryRepository
import com.alexeykatsuro.diaryofilms.util.Event
import com.alexeykatsuro.diaryofilms.util.extensions.triggerEvent
import com.alexeykatsuro.diaryofilms.util.input.createForm
import kotlinx.coroutines.launch
import javax.inject.Inject

class AdoptFilmViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
) : BaseViewModel() {

    val inputForm: AportInputForm = createForm(::AportInputForm){
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