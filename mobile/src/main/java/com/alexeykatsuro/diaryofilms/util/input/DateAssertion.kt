package com.alexeykatsuro.diaryofilms.util.input

import com.alexeykatsuro.inputfromutil.validation.InputValidator
import com.alexeykatsuro.inputfromutil.validation.TextAssertion
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateAssertion(datePattern: String) : TextAssertion() {

    private val formatter = SimpleDateFormat(datePattern, Locale.getDefault())

    override val defaultErrorMessage: String = "Invalid date format"

    //TODO It can pass invalid date like '99.99.9999'
    override fun isValid(param: String): Boolean {
        return try {
            formatter.parse(param).time
            true
        } catch (e: ParseException) {
            false
        }
    }

}

fun InputValidator.isDate(datePattern: String): TextAssertion =
    assert(DateAssertion(datePattern))