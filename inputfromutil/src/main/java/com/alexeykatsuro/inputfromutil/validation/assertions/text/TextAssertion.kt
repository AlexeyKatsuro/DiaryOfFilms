package com.alexeykatsuro.inputfromutil.validation.assertions.text

import com.alexeykatsuro.inputfromutil.validation.assertions.Assertion

abstract class TextAssertion : Assertion<String>() {
    abstract override fun isValid(param: String): Boolean
}