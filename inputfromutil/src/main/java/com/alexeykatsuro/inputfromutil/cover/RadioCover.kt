package com.alexeykatsuro.inputfromutil.cover

import android.widget.RadioButton
import com.alexeykatsuro.inputfromutil.OnValueChange
import com.google.android.material.textfield.TextInputLayout

data class RadioCover(val isChecked: Boolean, val onCheckedChange: OnValueChange<Boolean>?) :
    ViewCover<RadioButton> {
    override fun apply(view: RadioButton) {
        //Clear listener for setting new isChecked state, for avoid unnecessary old listener call
        view.setOnCheckedChangeListener(null)
        view.isChecked = isChecked
        if (onCheckedChange != null) {
            view.setOnCheckedChangeListener { _, isChecked ->
                onCheckedChange.onChanged(isChecked)
            }
        }
    }
}

fun RadioButton.applyCover(cover: RadioCover?) = cover?.apply(this)