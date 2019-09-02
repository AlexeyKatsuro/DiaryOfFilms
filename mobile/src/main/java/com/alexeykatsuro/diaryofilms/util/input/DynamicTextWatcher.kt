package com.alexeykatsuro.diaryofilms.util.input

import android.text.Editable
import android.text.TextWatcher

class DynamicTextWatcher(
    var afterChanged: ((String) -> Unit)? = null,
    var beforeChanged: ((String) -> Unit)? = null,
    var onChanged: ((String) -> Unit)? = null
) : TextWatcher {

    override fun afterTextChanged(s: Editable?) {
        afterChanged?.invoke(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeChanged?.invoke(s.toString())
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onChanged?.invoke(s.toString())
    }
}