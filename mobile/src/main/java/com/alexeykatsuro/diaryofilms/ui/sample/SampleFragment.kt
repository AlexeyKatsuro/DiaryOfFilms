package com.alexeykatsuro.diaryofilms.ui.sample

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.alexeykatsuro.diaryofilms.base.BindingInflater
import com.alexeykatsuro.diaryofilms.base.mvrx.DofMvRxFragment
import com.alexeykatsuro.diaryofilms.databinding.LayoutSampleBinding
import com.alexeykatsuro.diaryofilms.util.extensions.lazyFast
import timber.log.Timber
import javax.inject.Inject

class SampleFragment : DofMvRxFragment<LayoutSampleBinding>() {


    @Inject
    lateinit var factory: SampleViewModel.Factory

    private val controller by lazyFast { epoxyController() }

    private val viewModel: SampleViewModel by fragmentViewModel()

    override val inflater: BindingInflater<LayoutSampleBinding>
        get() = LayoutSampleBinding::inflate

    override fun invalidate() = withState(viewModel) {
        controller.setData(it)
    }

    private fun epoxyController(): SampleController {
        return SampleController().apply { callbacks = controllerCallbacks }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        withBinding {
            onNavUpClick = View.OnClickListener {
                navController.navigateUp()
            }

            inputRv.setController(controller)
        }
    }


    val controllerCallbacks = object : SampleController.Callbacks {

        override fun onSend() = withState(viewModel) {
            Timber.d("$it")
        }

        override fun onSellerNameChanged(text: String) {
            viewModel.updateState { copy(sellerName = text) }
        }

        override fun onSellerAddressChanged(text: String) {
            viewModel.updateState { copy(sellerAddress = text) }
        }

        override fun onSellerBankChanged(text: String) {
            viewModel.updateState { copy(sellerBank = text) }
        }

        override fun onSellerBankAddressChanged(text: String) {
            viewModel.updateState { copy(sellerBankAddress = text) }
        }
    }
}