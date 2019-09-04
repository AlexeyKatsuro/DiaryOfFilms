package com.alexeykatsuro.data.mappers

import com.alexeykatsuro.data.db.entity.FilmRecordEntity
import com.alexeykatsuro.data.dto.FilmRecord
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmRecordDemapper @Inject constructor() : Mapper<FilmRecord, FilmRecordEntity> {
    override fun map(from: FilmRecord): FilmRecordEntity {
        return FilmRecordEntity(
            id = from.id,
            subjectiveRating = from.subjectiveRating,
            watchingDate = from.watchingDate,
            title = from.filmInfo.title,
            rating = from.filmInfo.rating,
            year = from.filmInfo.year
        )
    }
}