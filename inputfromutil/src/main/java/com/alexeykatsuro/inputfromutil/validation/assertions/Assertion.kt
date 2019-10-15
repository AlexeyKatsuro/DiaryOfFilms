package com.alexeykatsuro.inputfromutil.validation.assertions

abstract class Assertion<P> {
    private var _errorMessage: String? = null

    var failureMessage: String
        get() = _errorMessage ?: defaultErrorMessage
        set(value) {
            _errorMessage = value
        }

    abstract fun isValid(param: P): Boolean
    protected abstract val defaultErrorMessage: String


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









