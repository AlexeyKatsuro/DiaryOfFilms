package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import com.airbnb.mvrx.MvRxState

data class AdoptFilmState(
    val title : String = "",
    val year : String = "",
    val watchingDate : String = "",
    val rating : Float = 5.0f,
    val subjectiveRating : Float = 5.0f,
    val inputs: List<String> = List(20){
        "Item $it"
    }

): MvRxState