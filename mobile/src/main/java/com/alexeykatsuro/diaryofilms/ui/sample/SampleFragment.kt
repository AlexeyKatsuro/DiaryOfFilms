package com.alexeykatsuro.diaryofilms.ui.sample

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.base.BindingInflater
import com.alexeykatsuro.diaryofilms.base.mvrx.DofMvRxFragment
import com.alexeykatsuro.diaryofilms.databinding.LayoutSampleBinding
import com.alexeykatsuro.diaryofilms.util.extensions.lazyFast
import com.alexeykatsuro.diaryofilms.util.extensions.toast
import com.alexeykatsuro.inputfromutil.OnValueChange
import com.alexeykatsuro.inputfromutil.StateProvider
import com.alexeykatsuro.inputfromutil.createFrom
import com.alexeykatsuro.inputfromutil.validation.Condition
import com.alexeykatsuro.inputfromutil.validation.assert
import com.alexeykatsuro.inputfromutil.validation.assertions.errorMessage
import com.alexeykatsuro.inputfromutil.validation.isNotEmpty
import com.alexeykatsuro.inputfromutil.validation.isNumber
import timber.log.Timber
import javax.inject.Inject

class SampleFragment : DofMvRxFragment<LayoutSampleBinding>() {


    @Inject
    lateinit var factory: SampleViewModel.Factory

    private val controller by lazyFast { epoxyController() }

    private val form by lazyFast { makeForm() }

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
            if (form.validate()) {
                toast("ok")
            }
        }

        override val onSellerNameChanged = OnValueChange<String> {
            viewModel.updateState { copy(sellerName = it, sellerNameError = null) }
        }

        override val onSellerAddressChanged = OnValueChange<String> {
            viewModel.updateState { copy(sellerAddress = it, sellerAddressError = null) }
        }

        override val onSellerBankChanged = OnValueChange<String> {
            viewModel.updateState { copy(sellerBank = it, sellerBankError = null) }
        }

        override val onSellerBankAddressChanged = OnValueChange<String> {
            viewModel.updateState { copy(sellerBankAddress = it, sellerBankAddressError = null) }
        }

        override val onContractNumberChanged = OnValueChange<String> {
            viewModel.updateState { copy(contractNumber = it) }
        }
        override val onContractDateChanged = OnValueChange<String> {
            viewModel.updateState { copy(contractDate = it) }
        }
        override val onAmountChanged = OnValueChange<String> {
            viewModel.updateState { copy(amount = it) }
        }
        override val onCurrencyChanged = OnValueChange<String> {
            viewModel.updateState { copy(currency = it) }
        }
        override val onAccountSellerChanged = OnValueChange<Boolean> {
            viewModel.updateState { copy(isAccountSeller = it, accountTypeError = null) }
        }
        override val onAccountBuyerChanged = OnValueChange<Boolean> {
            viewModel.updateState { copy(isAccountBuyer = it, accountTypeError = null) }
        }

        override val onPercentChanged = OnValueChange<Float> {
            viewModel.updateState { copy(interestRate = it, interestRateError = null) }
        }
    }

    private val stateProvider: StateProvider<SampleState> = { withState(viewModel) { it } }

    private fun makeForm() = createFrom(stateProvider) {

        withProp(SampleState::contractNumber, { copy(contractNumberError = it) }) {
            isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
        }
        withProp(SampleState::contractDate, { copy(contractDateError = it) }) {
            isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
        }
        withProp(SampleState::amount, { copy(amountError = it) }) {
            isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
        }
        withProp(SampleState::currency, { copy(currencyError = it) }) {
            isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
        }

        val isSellerCondition: Condition = { stateProvider().isAccountSeller }
        val isBuyerCondition: Condition = { stateProvider().isAccountBuyer }

        withProp(SampleState::sellerName, { copy(sellerNameError = it) }) {
            condition(isBuyerCondition) {
                isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
            }
        }

        withProp(SampleState::sellerAddress, { copy(sellerAddressError = it) }) {
            condition(isBuyerCondition) {
                isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
            }
        }

        withProp(SampleState::sellerBank, { copy(sellerBankError = it) }) {
            condition(isBuyerCondition) {
                isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
            }
        }

        withProp(SampleState::sellerBankAddress, { copy(sellerBankAddressError = it) }) {
            condition(isBuyerCondition) {
                isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
            }
        }

        withProp(SampleState::interestRate, { copy(interestRateError = it) }) {
            condition(isSellerCondition) {
                isNumber().greaterThan(5f).errorMessage("Ставка должна быть > 5")
            }
        }

        withState(reducer = { copy(accountTypeError = it) }) {
            assert("Выберите тип") { it.isAccountBuyer || it.isAccountSeller }
        }

        onStateChange {
            Timber.e("onStateChange $it")
            viewModel.updateState { it }
        }
    }
}