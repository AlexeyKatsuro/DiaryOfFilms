package com.alexeykatsuro.inputfromutil.validation

import com.alexeykatsuro.inputfromutil.validation.assertions.Assertion
import com.alexeykatsuro.inputfromutil.validation.assertions.CustomAssertion
import com.alexeykatsuro.inputfromutil.validation.assertions.errorMessage
import com.alexeykatsuro.inputfromutil.validation.assertions.number.*
import com.alexeykatsuro.inputfromutil.validation.assertions.text.ContainOnlyDigitAssertion
import com.alexeykatsuro.inputfromutil.validation.assertions.text.LengthAssertion
import com.alexeykatsuro.inputfromutil.validation.assertions.text.NotEmptyAssertion

class Approver<T> {
    private val assertions = mutableListOf<Assertion<T>>()

    fun verify(param: T): ValidResult {
        val result = ValidResult()
        for (assertion in assertions) {
            val isValid = assertion.isValid(param)
            if (!isValid) {
                result.addFailureAssertion(assertion)
            }
        }
        return result
    }

    fun <A : Assertion<T>> assert(assertion: A): A {
        assertions.add(assertion)
        return assertion
    }

    fun clear() {
        assertions.clear()
    }
}

fun <T> Approver<T>.assert(errorMessage: String, condition: (T) -> Boolean) =
    assert(CustomAssertion(condition).errorMessage(errorMessage))

fun Approver<String>.isNotEmpty() = assert(NotEmptyAssertion())

fun Approver<String>.isNumber() = assert(TextNumberAssertion())
fun Approver<String>.isDecimal() = assert(TextDecimalAssertion())
fun Approver<String>.isFloat() = assert(TextFloatAssertion())

fun Approver<Float>.isNumber() = assert(FloatAssertion())
fun Approver<Double>.isNumber() = assert(DoubleAssertion())

fun Approver<String>.length() = assert(LengthAssertion())

fun Approver<String>.isContainOnlyDigit() = assert(ContainOnlyDigitAssertion())
