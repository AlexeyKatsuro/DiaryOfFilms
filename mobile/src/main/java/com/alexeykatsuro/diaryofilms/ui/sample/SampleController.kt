package com.alexeykatsuro.diaryofilms.ui.sample

import com.airbnb.epoxy.TypedEpoxyController
import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.ui.view.buttonModel
import com.alexeykatsuro.diaryofilms.ui.view.inputModel
import com.alexeykatsuro.inputfromutil.OnValueChange
import headerModel

class SampleController : TypedEpoxyController<SampleState>() {

    var callbacks: Callbacks? = null

    override fun buildModels(data: SampleState?) {
        if (data == null) return

        headerModel {
            id(R.string.sample_account)
            header(R.string.sample_account)
        }

        inputModel {
            id(R.string.sample_seller_name)
            hint(R.string.sample_seller_name)
            text(data.sellerName)
            errorEnabled(true)
            onChange(OnValueChange {
                callbacks?.onSellerNameChanged(it)
            })
        }
        inputModel {
            id(R.string.sample_seller_address)
            hint(R.string.sample_seller_address)
            text(data.sellerAddress)
            errorEnabled(true)
            onChange(OnValueChange {
                callbacks?.onSellerAddressChanged(it)
            })
        }
        inputModel {
            id(R.string.sample_seller_bank)
            hint(R.string.sample_seller_bank)
            text(data.sellerBank)
            errorEnabled(true)
            onChange(OnValueChange {
                callbacks?.onSellerBankChanged(it)
            })
        }
        inputModel {
            id(R.string.sample_seller_bank_address)
            hint(R.string.sample_seller_bank_address)
            text(data.sellerBankAddress)
            errorEnabled(true)
            onChange(OnValueChange {
                callbacks?.onSellerBankAddressChanged(it)
            })
        }
        buttonModel {
            id(R.string.send)
            text(R.string.send)
            onClick { _ -> callbacks?.onSend() }
        }
    }

    interface Callbacks {
        fun onSellerNameChanged(text: String)
        fun onSellerAddressChanged(text: String)
        fun onSellerBankChanged(text: String)
        fun onSellerBankAddressChanged(text: String)
        fun onSend()
    }
}