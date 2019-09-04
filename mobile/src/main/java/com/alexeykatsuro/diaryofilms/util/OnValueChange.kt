package com.alexeykatsuro.diaryofilms.util

interface OnValueChange<T> {
    fun onChanged(value: T)
}


inline fun <T> OnValueChange(crossinline block: (value: T) -> Unit) = object : OnValueChange<T> {
    override fun onChanged(value: T) = block(value)
}