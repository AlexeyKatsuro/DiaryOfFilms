package com.alexeykatsuro.diaryofilms.ui.sample

import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.alexeykatsuro.diaryofilms.base.mvrx.DofMvRxViewModel
import com.alexeykatsuro.diaryofilms.ui.adoptfilm.AdoptFilmState
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class SampleViewModel @AssistedInject constructor(
    @Assisted initialState: SampleState
) : DofMvRxViewModel<SampleState>(initialState) {


    fun updateState(reducer: SampleState.() -> SampleState){
        setState(reducer)
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: SampleState): SampleViewModel
    }

    companion object : MvRxViewModelFactory<SampleViewModel, SampleState> {

        override fun create(
            viewModelContext: ViewModelContext,
            state: SampleState
        ): SampleViewModel? {
            val fragment: SampleFragment = (viewModelContext as FragmentViewModelContext).fragment()
            return fragment.factory.create(state)
        }
    }

}