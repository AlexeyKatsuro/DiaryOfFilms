package com.alexeykatsuro.diaryofilms.di.modue

import android.content.Context
import com.alexeykatsuro.diaryofilms.base.DiaryOfFilmsApp
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: DiaryOfFilmsApp): Context = application.applicationContext

}
