package com.alexeykatsuro.inputfromutil.validation.assertions


class CustomAssertion<T>(private val condition: (T) -> Boolean) : Assertion<T>() {
    override val defaultErrorMessage: String
        get() = "no description set"

    override fun isValid(param: T): Boolean = condition(param)
}
