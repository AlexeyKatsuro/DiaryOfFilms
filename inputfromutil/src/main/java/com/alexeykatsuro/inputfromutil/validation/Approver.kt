package com.alexeykatsuro.inputfromutil.validation

class Approver {
    private val assertions = mutableListOf<TextAssertion>()

    fun verify(text: String): ValidResult {
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

fun Approver.isNotEmpty() = assert(NotEmptyAssertion())

fun Approver.isNumber() = assert(NumberAssertion())

fun Approver.length() = assert(LengthAssertion())

fun Approver.isContainOnlyDigit() = assert(ContainOnlyDigitAssertion())