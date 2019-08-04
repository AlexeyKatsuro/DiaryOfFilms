package com.alexeykatsuro.diaryofilms.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alexeykatsuro.diaryofilms.data.db.dao.FilmRecordDao
import com.alexeykatsuro.diaryofilms.data.db.entity.FilmRecordEntity

@Database(entities = [FilmRecordEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun filmRecordDao(): FilmRecordDao
}