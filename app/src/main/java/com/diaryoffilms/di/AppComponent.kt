package com.diaryoffilms.di

import com.diaryoffilms.base.DiaryOfFilmsApp
import com.diaryoffilms.di.modue.AppModule
import com.diaryoffilms.di.modue.StorageModule
import com.diaryoffilms.ui.MainBuilder
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        MainBuilder::class,
        StorageModule::class
    ]
)
interface AppComponent : AndroidInjector<DiaryOfFilmsApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DiaryOfFilmsApp>()
}

