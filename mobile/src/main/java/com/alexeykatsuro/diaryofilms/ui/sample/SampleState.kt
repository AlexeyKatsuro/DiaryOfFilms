package com.alexeykatsuro.diaryofilms.ui.sample

import com.airbnb.mvrx.MvRxState

data class SampleState(
    val sellerName: String = "",
    val sellerAddress: String = "",
    val sellerBank: String = "",
    val sellerBankAddress: String = ""
) :MvRxState