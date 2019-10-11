package com.alexeykatsuro.diaryofilms.ui.sample

import com.alexeykatsuro.diaryofilms.di.scope.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class SampleBuilder {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeSampleFragment(): SampleFragment

}