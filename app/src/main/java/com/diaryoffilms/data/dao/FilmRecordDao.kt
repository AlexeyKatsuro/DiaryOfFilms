package com.diaryoffilms.data.dao

import androidx.room.*
import com.diaryoffilms.data.entity.FilmRecord


@Dao
interface FilmRecordDao {
    @Query("SELECT * FROM FilmRecord")
    fun getAll(): List<FilmRecord>

    @Query("SELECT * FROM filmrecord WHERE id = :id")
    fun getById(id: Long): FilmRecord

    @Insert
    fun insert(filmRecord: FilmRecord)

    @Update
    fun update(filmRecord: FilmRecord)

    @Delete
    fun delete(filmRecord: FilmRecord)
}