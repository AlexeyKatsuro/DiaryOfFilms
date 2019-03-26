package com.diaryoffilms.ui

import com.diaryoffilms.di.ViewModelBuilder
import com.diaryoffilms.di.scope.ActivityScoped
import com.diaryoffilms.ui.history.HistoryBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainBuilder {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class,
            HistoryBuilder::class
        ]
    )
    abstract fun mainActivityInjector(): MainActivity
}