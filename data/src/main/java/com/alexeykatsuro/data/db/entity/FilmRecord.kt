package com.alexeykatsuro.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class FilmRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val year: Int,
    val rating: Float,
    val subjectiveRating: Float,
    var watchingDate: Date
)