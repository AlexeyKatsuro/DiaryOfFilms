package com.alexeykatsuro.data

import androidx.room.TypeConverter
import java.util.*


class Converters {
    @TypeConverter
    fun toTimestamp(date: Date): Long = date.time

    @TypeConverter
    fun toDate(time: Long): Date = Date(time)
}