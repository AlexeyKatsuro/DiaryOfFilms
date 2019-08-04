package com.alexeykatsuro.diaryofilms.data.db.dao

import androidx.room.*
import com.alexeykatsuro.diaryofilms.data.db.entity.FilmRecordEntity


@Dao
interface FilmRecordDao {
    @Query("SELECT * FROM FilmRecordEntity")
    suspend fun getAll(): List<FilmRecordEntity>

    @Query("SELECT * FROM filmrecordentity WHERE id = :id")
    suspend fun getById(id: Long): FilmRecordEntity

    @Insert
    suspend fun insert(filmRecord: FilmRecordEntity)

    @Update
    suspend fun update(filmRecord: FilmRecordEntity)

    @Delete
    suspend fun delete(filmRecord: FilmRecordEntity)
}