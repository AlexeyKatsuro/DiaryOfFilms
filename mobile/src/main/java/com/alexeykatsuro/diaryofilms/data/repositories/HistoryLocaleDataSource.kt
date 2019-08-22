package com.alexeykatsuro.diaryofilms.data.repositories

import com.alexeykatsuro.diaryofilms.data.db.dao.FilmRecordDao
import com.alexeykatsuro.diaryofilms.data.dto.FilmRecord
import com.alexeykatsuro.diaryofilms.data.mappers.FilmRecordDemapper
import com.alexeykatsuro.diaryofilms.data.mappers.FilmRecordMapper
import com.alexeykatsuro.diaryofilms.data.mappers.toListMapper
import com.alexeykatsuro.diaryofilms.util.extensions.map
import javax.inject.Inject

class HistoryLocaleDataSource @Inject constructor(
    private val filmRecordDao: FilmRecordDao,
    private val filmRecordMapper: FilmRecordMapper,
    private val filmRecordDemapper: FilmRecordDemapper
) : DataSource {

    fun allFilms() = filmRecordDao.allFilms().map {
        filmRecordMapper.toListMapper().map(it)
    }

    suspend fun getAll() = withSuspendMap(filmRecordMapper.toListMapper()) {
        filmRecordDao.getAll()
    }

    suspend fun getById(id: Long) = withSuspendMap(filmRecordMapper) {
        filmRecordDao.getById(id)
    }

    suspend fun insert(filmRecord: FilmRecord) =
        filmRecordDao.insert(filmRecordDemapper.map(filmRecord))

    suspend fun update(filmRecord: FilmRecord) =
        filmRecordDao.update(filmRecordDemapper.map(filmRecord))

    suspend fun delete(filmRecord: FilmRecord) =
        filmRecordDao.delete(filmRecordDemapper.map(filmRecord))
}