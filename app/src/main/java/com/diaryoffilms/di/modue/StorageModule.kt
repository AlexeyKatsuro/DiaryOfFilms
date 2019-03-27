package com.diaryoffilms.di.modue

import android.content.Context
import androidx.room.Room
import com.diaryoffilms.data.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, AppDataBase::class.java.name).build()

    @Provides
    fun provideFilmRecordDao(dataBase: AppDataBase) = dataBase.filmRecordDao()
//
//    @Singleton
//    @Provides
//    fun providePreferences(context: Context): PreferenceStorage =
//        SharedPreferenceStorage(context)
}