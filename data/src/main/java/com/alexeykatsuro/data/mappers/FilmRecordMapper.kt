package com.alexeykatsuro.data.mappers

import com.alexeykatsuro.data.db.entity.FilmRecordEntity
import com.alexeykatsuro.data.dto.FilmInfo
import com.alexeykatsuro.data.dto.FilmRecord
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmRecordMapper @Inject constructor() : Mapper<FilmRecordEntity, FilmRecord> {
    override fun map(from: FilmRecordEntity): FilmRecord {
        return FilmRecord(
            id = from.id,
            subjectiveRating = from.subjectiveRating,
            watchingDate = from.watchingDate,
            filmInfo = FilmInfo(
                title = from.title,
                rating = from.rating,
                year = from.year
            )
        )
    }
}