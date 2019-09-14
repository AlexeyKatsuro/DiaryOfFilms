package com.alexeykatsuro.inputfromutil.validation

class InputValidator {
    private val assertions = mutableListOf<TextAssertion>()

    fun validate(text: String): ValidResult {
        val result = ValidResult()
        for (assertion in assertions) {
            val isValid = assertion.isValid(text)
            if (!isValid) {
                result.addFailureAssertion(assertion)
            }
        }
        return result
    }

    fun <A : TextAssertion> assert(assertion: A): A {
        assertions.add(assertion)
        return assertion
    }

    fun clear() {
        assertions.clear()
    }
}

fun InputValidator.isNotEmpty() = assert(NotEmptyAssertion())

fun InputValidator.isNumber() = assert(NumberAssertion())

fun InputValidator.length() = assert(LengthAssertion())

fun InputValidator.isContainOnlyDigit() = assert(ContainOnlyDigitAssertion())