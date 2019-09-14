package com.alexeykatsuro.diaryofilms.ui.history

import com.alexeykatsuro.diaryofilms.di.scope.FragmentScoped
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class HistoryBuilder {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeHistoryFragment(): HistoryFragment
}