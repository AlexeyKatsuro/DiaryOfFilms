package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import com.alexeykatsuro.diaryofilms.util.input.InputForm
import com.alexeykatsuro.diaryofilms.util.input.InputState

data class AdoptInputState(
    val title: InputState,
    val year: InputState,
    val rating: InputState,
    val watchingDate: InputState,
    val subjectiveRating: InputState
)

class AportInputForm : InputForm<AdoptInputState>() {

    val title = inputField { copy(title = it) }
    val year = inputField { copy(year = it) }
    val rating = inputField { copy(rating = it) }
    val watchingDate = inputField { copy(watchingDate = it) }
    val subjectiveRating = inputField { copy(subjectiveRating = it) }

    override fun createState() =
        AdoptInputState(
            title.state,
            year.state,
            rating.state,
            watchingDate.state,
            subjectiveRating.state
        )

}