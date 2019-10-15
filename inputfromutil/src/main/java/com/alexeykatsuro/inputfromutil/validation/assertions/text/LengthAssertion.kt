package com.alexeykatsuro.inputfromutil.validation.assertions.text

import com.alexeykatsuro.inputfromutil.validation.assertions.appendIf

class LengthAssertion internal constructor() : TextAssertion() {
    private var exactly: Int? = null
    private var lessThan: Int? = null
    private var atMost: Int? = null
    private var atLeast: Int? = null
    private var greaterThan: Int? = null

    /** Asserts the length is an exact (=) value. */
    fun exactly(length: Int): LengthAssertion {
        exactly = length
        return this
    }

    /** Asserts the length is less than (<) a value. */
    fun lessThan(length: Int): LengthAssertion {
        lessThan = length
        return this
    }

    /** Asserts the length is at most (<=) a value. */
    fun atMost(length: Int): LengthAssertion {
        atMost = length
        return this
    }

    /** Asserts the length is at least (>=) a value. */
    fun atLeast(length: Int): LengthAssertion {
        atLeast = length
        return this
    }

    /** Asserts the length is greater (>) than a value. */
    fun greaterThan(length: Int): LengthAssertion {
        greaterThan = length
        return this
    }

    override fun isValid(param: String): Boolean {
        val length = param
            .length
        if (exactly != null) {
            return length == exactly!!
        }
        if (lessThan != null && length >= lessThan!!) return false
        if (atMost != null && length > atMost!!) return false
        if (atLeast != null && length < atLeast!!) return false
        if (greaterThan != null && length <= greaterThan!!) return false
        return true
    }

    override val defaultErrorMessage: String
        get() {
            val descriptionBuilder = StringBuilder().apply {
                if (exactly != null) {
                    append("exactly $exactly")
                } else {
                    appendIf(atMost != null, "at most $atMost")
                    appendIf(atLeast != null, "at least $atLeast")
                    appendIf(greaterThan != null, "greater than $greaterThan")
                    appendIf(lessThan != null, "less than $lessThan")
                }
            }
            if (descriptionBuilder.isEmpty()) {
                return "no length bound set"
            }
            return descriptionBuilder.insert(0, "length must be ")
                .toString()
        }
}