package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import com.airbnb.mvrx.MvRxState

data class AdoptFilmState(
    val title : String = "",
    val year : String = "",
    val rating : Float = 5.0f,
    val watchingDate : String = "",
    val subjectiveRating : Float = 5.0f
): MvRxState