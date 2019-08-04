@file:JvmName("DateUtils")

package com.alexeykatsuro.diaryofilms.util.extensions

import java.text.SimpleDateFormat
import java.util.*


fun Date.toFormattedString(pattern: String): String =
    SimpleDateFormat(pattern,Locale.getDefault()).format(this)
