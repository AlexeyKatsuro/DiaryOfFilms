package com.alexeykatsuro.diaryofilms.data.dto

import java.util.*

data class FilmRecord(
    val id: Long = 0,
    val filmInfo: FilmInfo,
    val subjectiveRating: Float,
    var watchingDate: Date
){
    constructor(
         id: Long = 0,
         title: String,
         year: Int,
         rating: Float,
         subjectiveRating: Float,
         watchingDate: Date
    ): this(
        id = id,
        watchingDate = watchingDate,
        subjectiveRating = subjectiveRating,
        filmInfo = FilmInfo(
            title = title,
            year = year,
            rating = rating
        )
    )


}