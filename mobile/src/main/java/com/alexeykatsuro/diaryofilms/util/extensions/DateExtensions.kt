@file:JvmName("DateUtils")

package com.alexeykatsuro.diaryofilms.util.extensions

import android.app.DatePickerDialog
import android.content.Context
import androidx.fragment.app.Fragment
import com.alexeykatsuro.diaryofilms.R
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun Date.toFormattedString(pattern: String): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(this)

typealias DatePickerOptions = DatePickerDialog.() -> Unit

//const val defaultDatePattern = "dd.MM.yyyy"
const val datePatternRes = R.string.date_pattern

fun DatePickerDialog.currentMaxDateOptions() {
    datePicker.maxDate = Calendar.getInstance().timeInMillis
}

fun DatePickerDialog.currentMinDateOptions() {
    datePicker.minDate = Calendar.getInstance().timeInMillis
}

fun Calendar.plus(field: Int, amount: Int): Calendar {
    add(field, amount)
    return this
}

val DatePickerDialog.currentDate: Calendar
    get() = Calendar.getInstance()

fun Context.showDatePickerDialog(
    pattern: String = getString(datePatternRes),
    options: DatePickerOptions = { },
    currentDate: Date = Calendar.getInstance().time,
    callback: (date: String) -> Unit
) {
    val onDateSelected = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val calendar = Calendar.getInstance().apply { set(year, month, day) }
        val date = calendar.time.toFormattedString(pattern)
        callback(date)
    }

    val calendar = Calendar.getInstance()
    calendar.time = currentDate
    val (year, month, day) = calendar.getYearMonthDay()

    DatePickerDialog(this, onDateSelected, year, month, day).apply(options).show()
}

fun Fragment.showDatePickerDialog(
    pattern: String = getString(datePatternRes),
    options: DatePickerOptions = { },
    currentDate: Date = Calendar.getInstance().time,
    callback: (date: String) -> Unit
) {
    requireContext().showDatePickerDialog(pattern, options, currentDate, callback)
}

fun String.parseDate(pattern: String): Date {
    return SimpleDateFormat(pattern, Locale.getDefault()).parse(this)
}

fun String.parseDateOrNull(pattern: String): Date? {
    return try {
        SimpleDateFormat(pattern, Locale.getDefault()).parse(this)
    } catch (e: ParseException) {
        Timber.e(e)
        null
    }
}

fun Calendar.getYearMonthDay(): Triple<Int, Int, Int> {
    return Triple(
        get(Calendar.YEAR),
        get(Calendar.MONTH),
        get(Calendar.DAY_OF_MONTH)
    )
}

fun String.convertDateFromToPattern(fromPattern: String, toPattern: String): String? {
    return parseDateOrNull(fromPattern)?.toFormattedString(toPattern)
}
