package com.alexeykatsuro.diaryofilms.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alexeykatsuro.diaryofilms.data.dto.FilmInfo
import java.util.*

@Entity

data class FilmRecord(
    @Embedded
    val filmInfo: FilmInfo,
    val subjectiveRating: Int,
    var watchingDate: Date
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}