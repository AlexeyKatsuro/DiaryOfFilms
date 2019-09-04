package com.alexeykatsuro.diaryofilms.ui.history

import com.alexeykatsuro.diaryofilms.di.scope.FragmentScoped
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class HistoryBuilder {

    @FragmentScoped
    @ContributesAndroidInjector(
        modules = [
            HistoryAssistedModule::class]
    )
    internal abstract fun contributeHistoryFragment(): HistoryFragment
}

@Module(includes = [AssistedInject_HistoryAssistedModule::class])
@AssistedModule
interface HistoryAssistedModule