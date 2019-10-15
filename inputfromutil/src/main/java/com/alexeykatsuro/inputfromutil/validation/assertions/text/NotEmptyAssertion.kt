package com.alexeykatsuro.inputfromutil.validation.assertions.text

class NotEmptyAssertion : TextAssertion() {

    override fun isValid(param: String): Boolean = param.isNotEmpty()

    override val defaultErrorMessage: String = "can't be empty"
}