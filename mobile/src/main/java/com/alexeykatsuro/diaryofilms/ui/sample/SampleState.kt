package com.alexeykatsuro.diaryofilms.ui.sample

import com.airbnb.mvrx.MvRxState

data class SampleState(
    val contractNumber: String = "",
    val contractDate: String = "",

    val amount: String = "",
    val currency: String = "",

    val isAccountSeller: Boolean = false,
    val isAccountBuyer: Boolean = false,
    val accountTypeError: String? = null,

    val interestRate:  Float = 0.0f,
    val interestRateError:  String? = null,

    val sellerName: String = "",
    val sellerNameError: String? = null,

    val sellerAddress: String = "",
    val sellerAddressError: String? = null,

    val sellerBank: String = "",
    val sellerBankError: String? = null,

    val sellerBankAddress: String = "",
    val sellerBankAddressError: String? = null
) :MvRxState