package com.alexeykatsuro.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alexeykatsuro.data.db.entity.FilmRecordEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FilmRecordDao {

    @Query("SELECT * FROM filmRecordEntity ORDER BY watchingDate DESC")
    fun allFilmsObservable(): Flow<List<FilmRecordEntity>>

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