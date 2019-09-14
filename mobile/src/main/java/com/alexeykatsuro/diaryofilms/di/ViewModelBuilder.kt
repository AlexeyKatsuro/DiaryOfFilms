package com.alexeykatsuro.diaryofilms.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelBuilder {
    @Binds
    internal abstract fun bindViewModelFactory(factory: DOFViewModelFactory): ViewModelProvider.Factory
}
