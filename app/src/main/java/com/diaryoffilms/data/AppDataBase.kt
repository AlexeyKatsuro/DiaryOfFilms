package com.diaryoffilms.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.diaryoffilms.data.dao.FilmRecordDao
import com.diaryoffilms.data.entity.FilmRecord

@Database(entities = [FilmRecord::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase(){
    abstract fun filmRecordDao(): FilmRecordDao
}