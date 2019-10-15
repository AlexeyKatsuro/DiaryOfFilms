package com.alexeykatsuro.inputfromutil.validation.assertions.text


class ContainOnlyDigitAssertion : TextAssertion() {
    override val defaultErrorMessage: String = "Must contain only numbers"

    override fun isValid(param: String): Boolean = param.all(Char::isDigit)
}