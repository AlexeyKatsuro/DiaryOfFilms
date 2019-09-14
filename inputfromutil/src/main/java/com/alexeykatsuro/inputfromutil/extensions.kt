package com.alexeykatsuro.inputfromutil

import android.text.Spanned
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.ListenerUtil
import com.google.android.material.textfield.TextInputLayout


/**
 * If the text changed then we move the cursor to the end of the new text. This allows us to fill in text programmatically if needed,
 * like a search suggestion, but if the user is typing and the view is rebound we won't lose their cursor position.
 *
 * @param charSequence
 */
@BindingAdapter("updateText")
fun EditText.setTextAndCursor(charSequence: CharSequence?) {
    if (setTextIfNew(charSequence)) {
        setSelection(length())
    }
}

/**
 * @return true if [charSequence] is different from current text in [TextView] receiver [charSequence] will be wrote in receiver
 * else false, it means that text has the same [charSequence] and it will not emit an event of onTextChanged
 */
fun TextView.setTextIfNew(charSequence: CharSequence?): Boolean =
    if (isTextDifferent(charSequence, text)) {
        text = charSequence
        true
    } else false // Previous text is the same

/**
 * @return True if str1 is different from str2.
 * This is adapted from how the Android DataBinding library binds its text views.
 */
fun isTextDifferent(str1: CharSequence?, str2: CharSequence?): Boolean {
    if (str1 === str2) {
        return false
    }
    if (str1 == null || str2 == null) {
        return true
    }
    val length = str1.length
    if (length != str2.length) {
        return true
    }

    if (str1 is Spanned) {
        return str1 != str2
    }

    for (i in 0 until length) {
        if (str1[i] != str2[i]) {
            return true
        }
    }
    return false
}

fun EditText.onChange(onChanged: ((String) -> Unit)?) {
    val watcher = ListenerUtil.getListener<DynamicTextWatcher>(this, R.id.tag_text_dynamic_watcher)

    if (watcher != null) {
        watcher.onChanged = onChanged
    } else {
        val newWatcher = DynamicTextWatcher(onChanged)
        addTextChangedListener(newWatcher)
        ListenerUtil.trackListener(this, newWatcher, R.id.tag_text_dynamic_watcher)
    }
}

/**
 * Combined method with [setTextAndCursor] and [onChange] functionality
 */
@BindingAdapter("bindInputState")
fun EditText.bindInputState(field: InputState?) {
    if (field != null) {
        setTextAndCursor(field.text)
        onChange(field.onTextChange)
    }
}

@BindingAdapter("bindInputState")
fun TextInputLayout.bindInputLayoutState(field: InputState?) {
    if (field != null) {
        editText!!.setTextAndCursor(field.text)
        editText!!.onChange(field.onTextChange)
        if (error != field.errorMessage) {
            error = field.errorMessage
        }
    }
}

@BindingAdapter("updateInput")
fun TextInputLayout.updateInput(field: Input?) {
    if (field != null) {
        editText!!.setTextAndCursor(field.text)
        if (error != field.errorMessage) {
            error = field.errorMessage
        }
    }
}