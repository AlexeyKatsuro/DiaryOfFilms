package com.alexeykatsuro.diaryofilms.ui

import com.alexeykatsuro.diaryofilms.di.ViewModelBuilder
import com.alexeykatsuro.diaryofilms.di.scope.ActivityScoped
import com.alexeykatsuro.diaryofilms.ui.adoptfilm.AdoptFilmBuilder
import com.alexeykatsuro.diaryofilms.ui.history.HistoryBuilder
import com.alexeykatsuro.diaryofilms.ui.sample.SampleBuilder
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [AssistedInject_MainBuilder::class])
@AssistedModule
internal abstract class MainBuilder {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class,
            HistoryBuilder::class,
            AdoptFilmBuilder::class,
            SampleBuilder::class
        ]
    )
    abstract fun mainActivityInjector(): MainActivity
}