package com.alexeykatsuro.inputfromutil.validation

typealias Failures = List<Assertion<*>>
typealias onInvalidCallback = (Failures) -> Unit

class ValidResult {

    private val _failureAssertions = mutableListOf<Assertion<*>>()
    val failureAssertions: Failures
        get() = _failureAssertions

    val isValid: Boolean
        get() = _failureAssertions.isEmpty()
    val hasErrors: Boolean
        get() = !isValid

    val errorMessage: String?
        get() = _failureAssertions.messageOrNull()

    inline fun onValid(block: () -> Unit) {
        if (isValid) block()
    }

    inline fun onInvalid(block: onInvalidCallback) {
        if (hasErrors) block(failureAssertions)
    }

    fun addFailureAssertion(assertion: Assertion<*>) {
        _failureAssertions.add(assertion)
    }
}

fun Failures.messageOrNull(): String? = firstOrNull()?.errorMessage