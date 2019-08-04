package com.alexeykatsuro.diaryofilms.ui.history

import androidx.lifecycle.ViewModel
import com.alexeykatsuro.diaryofilms.di.ViewModelKey
import com.alexeykatsuro.diaryofilms.di.scope.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class HistoryBuilder {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeHistoryFragment(): HistoryFragment

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    internal abstract fun bindHistoryViewModel(historyViewModel: HistoryViewModel): ViewModel
}