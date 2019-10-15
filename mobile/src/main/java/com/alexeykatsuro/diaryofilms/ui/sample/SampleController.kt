package com.alexeykatsuro.diaryofilms.ui.sample

import com.airbnb.epoxy.TypedEpoxyController
import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.ui.view.buttonModel
import com.alexeykatsuro.diaryofilms.ui.view.errorTextModel
import com.alexeykatsuro.diaryofilms.ui.view.inputModel
import com.alexeykatsuro.diaryofilms.ui.view.ratingBarModel
import com.alexeykatsuro.diaryofilms.ui.view.sample.sampleBlock1Model
import com.alexeykatsuro.inputfromutil.OnValueChange
import com.alexeykatsuro.inputfromutil.cover.InputCover
import com.alexeykatsuro.inputfromutil.cover.RadioCover
import headerModel

class SampleController : TypedEpoxyController<SampleState>() {

    var callbacks: Callbacks? = null

    override fun buildModels(data: SampleState?) {
        if (data == null) return

        sampleBlock1Model {
            id("sampleBlock1Model")
            contractNumber(InputCover(data.contractNumber, callbacks?.onContractNumberChanged))
            contractDate(InputCover(data.contractDate, callbacks?.onContractDateChanged))
            amount(InputCover(data.amount, callbacks?.onAmountChanged))
            currency(InputCover(data.currency, callbacks?.onCurrencyChanged))
            isAccountSeller(RadioCover(data.isAccountSeller, callbacks?.onAccountSellerChanged))
            isAccountBuyer(RadioCover(data.isAccountBuyer, callbacks?.onAccountBuyerChanged))
            accountError(data.accountTypeError)

        }

        if(data.isAccountSeller) {
            ratingBarModel {
                id("ratingBarModel_1")
                title(R.string.sample_percent)
                rating(data.interestRate)
                onRatingChange(callbacks?.onPercentChanged)
            }
            if (!data.interestRateError.isNullOrEmpty()){
                errorTextModel {
                    id("interestRateError")
                    error(data.interestRateError)
                }
            }
        }

        if(data.isAccountBuyer) {
            headerModel {
                id(R.string.sample_account)
                header(R.string.sample_account)
            }

            inputModel {
                id(R.string.sample_seller_name)
                hint(R.string.sample_seller_name)
                text(data.sellerName)
                error(data.sellerNameError)
                errorEnabled(true)
                onChange(callbacks?.onSellerNameChanged)
            }
            inputModel {
                id(R.string.sample_seller_address)
                hint(R.string.sample_seller_address)
                text(data.sellerAddress)
                error(data.sellerAddressError)
                errorEnabled(true)
                onChange(callbacks?.onSellerAddressChanged)
            }
            inputModel {
                id(R.string.sample_seller_bank)
                hint(R.string.sample_seller_bank)
                text(data.sellerBank)
                error(data.sellerBankError)
                errorEnabled(true)
                onChange(callbacks?.onSellerBankChanged)
            }
            inputModel {
                id(R.string.sample_seller_bank_address)
                hint(R.string.sample_seller_bank_address)
                text(data.sellerBankAddress)
                error(data.sellerBankAddressError)
                errorEnabled(true)
                onChange(callbacks?.onSellerBankAddressChanged)
            }
        }
        buttonModel {
            id(R.string.send)
            text(R.string.send)
            onClick { _ -> callbacks?.onSend() }
        }
    }

    interface Callbacks {
        val onSellerNameChanged: OnValueChange<String>
        val onSellerAddressChanged: OnValueChange<String>
        val onSellerBankChanged: OnValueChange<String>
        val onSellerBankAddressChanged: OnValueChange<String>
        val onContractNumberChanged: OnValueChange<String>
        val onContractDateChanged: OnValueChange<String>
        val onAmountChanged: OnValueChange<String>
        val onCurrencyChanged: OnValueChange<String>
        val onAccountSellerChanged: OnValueChange<Boolean>
        val onAccountBuyerChanged: OnValueChange<Boolean>
        val onPercentChanged: OnValueChange<Float>

        fun onSend()
    }
}