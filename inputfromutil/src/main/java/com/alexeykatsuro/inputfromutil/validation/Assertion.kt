package com.alexeykatsuro.inputfromutil.validation

abstract class Assertion<P> {
    private var _errorMessage: String? = null

    var errorMessage: String
        get() = _errorMessage ?: defaultErrorMessage
        set(value) {
            _errorMessage = value
        }

    abstract fun isValid(param: P): Boolean
    protected abstract val defaultErrorMessage: String


}

abstract class TextAssertion : Assertion<String>() {
    abstract override fun isValid(param: String): Boolean
}

class NotEmptyAssertion : TextAssertion() {

    override fun isValid(param: String): Boolean = param.isNotEmpty()

    override val defaultErrorMessage: String = "can't be empty"
}

class NumberAssertion : TextAssertion() {
    private var exactly: Int? = null
    private var lessThan: Int? = null
    private var atMost: Int? = null
    private var atLeast: Int? = null
    private var greaterThan: Int? = null

    /** Asserts the number is an exact (=) value. */
    fun exactly(length: Int): NumberAssertion {
        exactly = length
        return this
    }

    /** Asserts the number is less than (<) a value. */
    fun lessThan(length: Int): NumberAssertion {
        lessThan = length
        return this
    }

    /** Asserts the number is at most (<=) a value. */
    fun atMost(length: Int): NumberAssertion {
        atMost = length
        return this
    }

    /** Asserts the number is at least (>=) a value. */
    fun atLeast(length: Int): NumberAssertion {
        atLeast = length
        return this
    }

    /** Asserts the number is greater (>) than a value. */
    fun greaterThan(length: Int): NumberAssertion {
        greaterThan = length
        return this
    }

    override fun isValid(param: String): Boolean {
        val intValue = param.toIntOrNull() ?: return false
        if (exactly != null && intValue != exactly!!) return false
        if (lessThan != null && intValue >= lessThan!!) return false
        if (atMost != null && intValue > atMost!!) return false
        if (atLeast != null && intValue < atLeast!!) return false
        if (greaterThan != null && intValue <= greaterThan!!) return false
        return true
    }

    override val defaultErrorMessage: String
        get() {
            val descriptionBuilder = StringBuilder().apply {
                appendIf(exactly != null, "exactly $exactly")
                appendIf(lessThan != null, "less than $lessThan")
                appendIf(atMost != null, "at most $atMost")
                appendIf(atLeast != null, "at least $atLeast")
                appendIf(greaterThan != null, "greater than $greaterThan")
            }
            if (descriptionBuilder.isEmpty()) {
                return "value must be a number"
            }
            return descriptionBuilder.insert(0, "value must be ")
                .toString()
        }
}

private fun StringBuilder.appendIf(
    condition: Boolean,
    s: String,
    separator: String = ", "
) {
    if (condition) {
        if (isNotEmpty()) {
            append(separator)
        }
        append(s)
    }
}

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

class ContainOnlyDigitAssertion : TextAssertion() {
    override val defaultErrorMessage: String = "Must contain only numbers"

    override fun isValid(param: String): Boolean = param.all(Char::isDigit)
}

