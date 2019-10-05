package com.alexeykatsuro.diaryofilms.util.epoxy


import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.withState
import com.alexeykatsuro.diaryofilms.base.mvrx.DofMvRxFragment
import com.alexeykatsuro.diaryofilms.base.mvrx.DofMvRxViewModel

class DofEpoxyController(val buildModelsCallback: DofEpoxyController.() -> Unit) :
    AsyncEpoxyController() {
    override fun buildModels() {
        buildModelsCallback()
    }
}

inline fun <S : MvRxState, VM : DofMvRxViewModel<S>> DofMvRxFragment<*>.simpleEpoxyController(
    viewModel: VM,
    crossinline build: DofEpoxyController.(state: S) -> Unit
) = DofEpoxyController {
    // Models are built asynchronously, so it is possible that this is called after the fragment
    // is detached under certain race conditions.
    if (view == null || isRemoving) return@DofEpoxyController
    withState(viewModel) { state ->
        build(state)
    }
}
