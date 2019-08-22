package com.alexeykatsuro.diaryofilms.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alexeykatsuro.diaryofilms.data.db.entity.FilmRecordEntity


@Dao
interface FilmRecordDao {

    @Query("SELECT * FROM filmRecordEntity ORDER BY watchingDate DESC")
    fun allFilms(): LiveData<List<FilmRecordEntity>>

    @Query("SELECT * FROM filmRecordEntity")
    suspend fun getAll(): List<FilmRecordEntity>

    @Query("SELECT * FROM filmRecordEntity WHERE id = :id")
    suspend fun getById(id: Long): FilmRecordEntity

    @Insert
    suspend fun insert(filmRecord: FilmRecordEntity)

    @Update
    suspend fun update(filmRecord: FilmRecordEntity)

    @Delete
    suspend fun delete(filmRecord: FilmRecordEntity)
}