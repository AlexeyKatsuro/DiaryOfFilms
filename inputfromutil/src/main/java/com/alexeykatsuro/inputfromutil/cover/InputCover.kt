package com.alexeykatsuro.inputfromutil.cover

import com.alexeykatsuro.inputfromutil.OnValueChange
import com.alexeykatsuro.inputfromutil.setInput
import com.google.android.material.textfield.TextInputLayout

data class InputCover(val text: CharSequence?, val onTextChanged: OnValueChange<String>?) :
    ViewCover<TextInputLayout> {
    override fun apply(view: TextInputLayout) {
        view.editText?.setInput(text, onTextChanged)
    }

}

fun TextInputLayout.applyCover(cover: InputCover?) = cover?.apply(this)
