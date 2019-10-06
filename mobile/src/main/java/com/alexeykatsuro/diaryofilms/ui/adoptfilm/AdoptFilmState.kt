package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import com.airbnb.mvrx.MvRxState

data class AdoptFilmState(
    val title : String = "",
    val titleError : String? = null,
    val year : String = "",
    val yearError : String? = null,
    val watchingDate : String = "",
    val watchingDateError : String? = null,
    val rating : Float = 5.0f,
    val subjectiveRating : Float = 5.0f,
    val inputs: List<String> = List(5){
        "Item $it"
    },
    val inputErrors: List<String?> = inputs.map { null }

): MvRxState