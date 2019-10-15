package com.alexeykatsuro.inputfromutil.validation.assertions

import com.alexeykatsuro.inputfromutil.validation.ConditionList
import com.alexeykatsuro.inputfromutil.validation.isAllMet


abstract class Assertion<P> {
    private var _errorMessage: String? = null
    internal var conditions: ConditionList? = null

    var failureMessage: String
        get() = _errorMessage ?: defaultErrorMessage
        set(value) {
            _errorMessage = value
        }

    abstract fun isValid(param: P): Boolean
    protected abstract val defaultErrorMessage: String

    /** Returns true if the assertion's condition returns true, or if there is no condition. */
    internal fun isConditionMet() = conditions.isAllMet()
}

fun <P, A : Assertion<P>> A.errorMessage(message: String): A {
    failureMessage = message
    return this
}

internal fun StringBuilder.appendIf(
    condition: Boolean,
    s: String,
    separator: String = ", "
) {
    if (condition) {
        if (isNotEmpty()) {
            append(separator)
        }
        append(s)
    }
}
