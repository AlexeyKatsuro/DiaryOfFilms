package com.alexeykatsuro.inputfromutil.validation

class ValidResult {

    private val failureAssertions = mutableListOf<Assertion<*>>()

    val isValid: Boolean
        get() = failureAssertions.isEmpty()
    val hasErrors: Boolean
        get() = !isValid

    val errorMessage: String?
        get() = failureAssertions.getOrNull(0)?.errorMessage

    fun onValid(block: () -> Unit) {
        if (isValid) block()
    }

    fun onInvalid(block: (List<Assertion<*>>) -> Unit) {
        if (hasErrors) block(failureAssertions)
    }

    fun addFailureAssertion(assertion: Assertion<*>) {
        failureAssertions.add(assertion)
    }
}
