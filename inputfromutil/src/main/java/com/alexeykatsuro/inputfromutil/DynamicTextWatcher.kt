package com.alexeykatsuro.inputfromutil

import android.text.Editable
import android.text.TextWatcher

class DynamicTextWatcher(
    var onValueChanged: OnValueChange<String>
) : TextWatcher {

    override fun afterTextChanged(s: Editable?) = Unit

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onValueChanged.onChanged(s?.toString() ?: "")
    }
}