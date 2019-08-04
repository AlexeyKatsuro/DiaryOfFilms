package com.alexeykatsuro.diaryofilms.di

import com.alexeykatsuro.diaryofilms.base.DiaryOfFilmsApp
import com.alexeykatsuro.diaryofilms.di.modue.AppModule
import com.alexeykatsuro.diaryofilms.di.modue.StorageModule
import com.alexeykatsuro.diaryofilms.ui.MainBuilder
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

