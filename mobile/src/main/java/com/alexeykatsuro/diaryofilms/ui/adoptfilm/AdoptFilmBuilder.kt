package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import androidx.lifecycle.ViewModel
import com.alexeykatsuro.diaryofilms.di.ViewModelKey
import com.alexeykatsuro.diaryofilms.di.scope.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class AdoptFilmBuilder {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeAdoptFilmFragment(): AdoptFilmFragment

    @Binds
    @IntoMap
    @ViewModelKey(AdoptFilmViewModel::class)
    internal abstract fun bindAdoptFilmViewModel(AdoptFilmViewModel: AdoptFilmViewModel): ViewModel
}