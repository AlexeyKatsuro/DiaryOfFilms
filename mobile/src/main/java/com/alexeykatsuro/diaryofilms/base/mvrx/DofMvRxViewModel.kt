package com.alexeykatsuro.diaryofilms.base.mvrx

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.alexeykatsuro.diaryofilms.BuildConfig

abstract class DofMvRxViewModel<S : MvRxState>(
    initialState: S
) : BaseMvRxViewModel<S>(initialState, debugMode = BuildConfig.DEBUG)