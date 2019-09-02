package com.alexeykatsuro.inputfromutil.validation

import com.alexeykatsuro.inputfromutil.InputState

class InputValidator {
    private val assertions = mutableListOf<TextAssertion>()

    fun validate(state: InputState): ValidResult {
        val result = ValidResult()
        for (assertion in assertions) {
            val isValid = assertion.isValid(state.text)
            if (!isValid) {
                result.addFailureAssertion(assertion)
            }
        }
        return result
    }

    fun isNotEmpty() = assert(NotEmptyAssertion())

    fun isNumber() = assert(NumberAssertion())

    fun length() = assert(LengthAssertion())

    fun isContainOnlyDigit() = assert(ContainOnlyDigitAssertion())

    fun <A : TextAssertion> assert(assertion: A): A {
        assertions.add(assertion)
        return assertion
    }

    fun clear() {
        assertions.clear()
    }
}