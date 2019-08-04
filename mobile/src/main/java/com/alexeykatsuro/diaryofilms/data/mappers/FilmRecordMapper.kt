package com.alexeykatsuro.diaryofilms.data.mappers

import com.alexeykatsuro.diaryofilms.data.db.entity.FilmRecordEntity
import com.alexeykatsuro.diaryofilms.data.dto.FilmInfo
import com.alexeykatsuro.diaryofilms.data.dto.FilmRecord

object FilmRecordMapper : Mapper<FilmRecordEntity, FilmRecord> {
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