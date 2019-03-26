package com.diaryoffilms.ui.history

import androidx.lifecycle.ViewModel
import com.diaryoffilms.di.ViewModelKey
import com.diaryoffilms.di.scope.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class HistoryBuilder {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeSettingsFragment(): HistoryFragment

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    internal abstract fun bindHistoryViewModel(historyViewModel: HistoryViewModel): ViewModel
}