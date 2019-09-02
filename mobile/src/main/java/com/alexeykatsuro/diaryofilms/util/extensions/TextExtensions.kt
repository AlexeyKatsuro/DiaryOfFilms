package com.alexeykatsuro.diaryofilms.util.extensions

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.redmadrobot.inputmask.MaskedTextChangedListener

var TextInputLayout.text: String
    get() = editText!!.text.toString()
    set(value) = editText!!.setText(value)

fun TextInputLayout.setMask(mask: String, showHint: Boolean = false, handleValue: (extractedValue: String) -> Unit) {
    editText!!.setMask(mask, showHint, handleValue)
}

fun EditText.setMask(mask: String, showHint: Boolean = false, handleValue: (extractedValue: String) -> Unit) {
    val listener = MaskedTextChangedListener.installOn(
        this,
        mask,
        object : MaskedTextChangedListener.ValueListener {
            override fun onTextChanged(maskFilled: Boolean, extractedValue: String) {
                handleValue(extractedValue)
            }
        }
    )
    if (showHint) hint = listener.placeholder()
}

