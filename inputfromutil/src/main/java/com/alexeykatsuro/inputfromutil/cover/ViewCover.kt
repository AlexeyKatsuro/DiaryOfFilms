package com.alexeykatsuro.inputfromutil.cover

import android.view.View

interface ViewCover<V: View> {
    fun apply(view: V)
}