package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import com.airbnb.mvrx.MvRxState
import com.alexeykatsuro.inputfromutil.Input

data class AdoptFilmState(
    val title : Input = Input(),
    val year : Input = Input(),
    val watchingDate : Input = Input(),
    val rating : Float = 5.0f,
    val subjectiveRating : Float = 5.0f
): MvRxState