package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import com.alexeykatsuro.diaryofilms.di.scope.FragmentScoped
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class AdoptFilmBuilder {

    @FragmentScoped
    @ContributesAndroidInjector()
    internal abstract fun contributeAdoptFilmFragment(): AdoptFilmFragment
}


