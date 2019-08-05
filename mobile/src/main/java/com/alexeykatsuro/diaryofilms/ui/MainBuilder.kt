package com.alexeykatsuro.diaryofilms.ui

import com.alexeykatsuro.diaryofilms.di.ViewModelBuilder
import com.alexeykatsuro.diaryofilms.di.scope.ActivityScoped
import com.alexeykatsuro.diaryofilms.ui.adoptfilm.AdoptFilmBuilder
import com.alexeykatsuro.diaryofilms.ui.history.HistoryBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainBuilder {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class,
            HistoryBuilder::class,
            AdoptFilmBuilder::class
        ]
    )
    abstract fun mainActivityInjector(): MainActivity
}