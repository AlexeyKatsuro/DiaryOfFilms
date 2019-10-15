package com.alexeykatsuro.inputfromutil.validation.assertions.number

import com.alexeykatsuro.inputfromutil.validation.assertions.Assertion
import com.alexeykatsuro.inputfromutil.validation.assertions.appendIf

abstract class NumberAssertion<T, N> : Assertion<T>() where N : Number, N: Comparable<N> {
    protected var exactly: N? = null
    protected var lessThan: N? = null
    protected var atMost: N? = null
    protected var atLeast: N? = null
    protected var greaterThan: N? = null

    /** Asserts the number is an exact (=) value. */
    fun exactly(length:  N): NumberAssertion<T, N> {
        exactly = length
        return this
    }

    /** Asserts the number is less than (<) a value. */
    fun lessThan(length:  N): NumberAssertion<T, N> {
        lessThan = length
        return this
    }

    /** Asserts the number is at most (<=) a value. */
    fun atMost(length:  N): NumberAssertion<T, N> {
        atMost = length
        return this
    }

    /** Asserts the number is at least (>=) a value. */
    fun atLeast(length:  N): NumberAssertion<T, N> {
        atLeast = length
        return this
    }

    /** Asserts the number is greater (>) than a value. */
    fun greaterThan(length:  N): NumberAssertion<T, N> {
        greaterThan = length
        return this
    }

    protected abstract fun T.toNumberOrNull(): N?

    override fun isValid(param: T): Boolean {
        val intValue = param.toNumberOrNull() ?: return false
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

class TextNumberAssertion: NumberAssertion<String,Int>(){
    override fun String.toNumberOrNull(): Int? = toIntOrNull()
}

class TextFloatAssertion: NumberAssertion<String,Float>(){
    override fun String.toNumberOrNull(): Float?  = toFloatOrNull()
}

class TextDecimalAssertion: NumberAssertion<String,Double>(){
    override fun String.toNumberOrNull(): Double?  = toDoubleOrNull()

}

class FloatAssertion: NumberAssertion<Float,Float>(){
    override fun Float.toNumberOrNull(): Float? = this
}

class DoubleAssertion: NumberAssertion<Double,Double>(){
    override fun Double.toNumberOrNull(): Double? = this
}