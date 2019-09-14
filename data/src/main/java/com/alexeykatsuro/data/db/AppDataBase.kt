package com.alexeykatsuro.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alexeykatsuro.data.Converters
import com.alexeykatsuro.data.db.dao.FilmRecordDao
import com.alexeykatsuro.data.db.entity.FilmRecordEntity

@Database(entities = [FilmRecordEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun filmRecordDao(): FilmRecordDao
}