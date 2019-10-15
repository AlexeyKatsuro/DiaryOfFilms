package com.alexeykatsuro.inputfromutil

import com.alexeykatsuro.inputfromutil.validation.Approver
import com.alexeykatsuro.inputfromutil.validation.ValidResult

class GenericFormItem<S, T>(
    val input: (S) -> T,
    val approver: Approver<T>,
    val reducer: ErrorReducer<S>
) {
    fun verify(state: S): ValidResult {
        val propertyValue = input(state)
        return approver.verify(propertyValue)
    }
}
