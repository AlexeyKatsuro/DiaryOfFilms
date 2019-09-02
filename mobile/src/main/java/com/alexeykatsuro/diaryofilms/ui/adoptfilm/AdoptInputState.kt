package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import com.alexeykatsuro.inputfromutil.InputForm
import com.alexeykatsuro.inputfromutil.InputState


data class AdoptInputState(
    val title: InputState,
    val year: InputState,
    val rating: InputState,
    val watchingDate: InputState,
    val subjectiveRating: InputState
)

class AdoptInputForm : InputForm<AdoptInputState>() {

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