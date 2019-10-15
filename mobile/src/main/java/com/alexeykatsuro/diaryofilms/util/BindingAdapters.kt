package com.alexeykatsuro.diaryofilms.util

import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.ListenerUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexeykatsuro.diaryofilms.util.extensions.addDividerItemDecoration
import com.alexeykatsuro.diaryofilms.util.extensions.setAnimatedVisibility
import com.alexeykatsuro.diaryofilms.util.extensions.setMask
import com.alexeykatsuro.inputfromutil.*

/**
 * Data Binding adapters specific to the app.
 */

@BindingAdapter("visibleGone")
fun showHide(view: View, show: Boolean) {
    view.isVisible = show
}

@BindingAdapter("visibleInvisible")
fun showHideInvisible(view: View, show: Boolean) {
    view.isInvisible = !show
}

@BindingAdapter("mask", "maskShowHint", "maskExtractedValueCallback", requireAll = false)
fun setMask(
    editText: EditText,
    mask: String,
    showHint: Boolean?,
    callback: ((extractedValue: String) -> Unit)?
) {
    editText.setMask(mask, showHint ?: false, callback ?: {})
}

@BindingAdapter("inflateMenu")
fun setMenu(toolbar: Toolbar, @MenuRes resId: Int?) {
    resId?.let { toolbar.inflateMenu(it) }
}

@BindingAdapter("visibleInvisibleAnim")
fun View.setAnimatedShowHideInvisible(visible: Boolean) {
    setAnimatedVisibility(if (visible) View.VISIBLE else View.INVISIBLE)
}

@BindingAdapter("visibleGoneAnim")
fun View.setAnimatedShowHide(visible: Boolean) {
    setAnimatedVisibility(if (visible) View.VISIBLE else View.GONE)
}

@BindingAdapter("defaultItemDecoration")
fun setDefaultItemDecoration(recyclerView: RecyclerView, required: Boolean) {
    if (required) {
        recyclerView.addDividerItemDecoration()
    }
}

@BindingAdapter("ratingView", "onRatingChange", requireAll = true)
fun bindRatingSeekbar(
    seekBar: SeekBar,
    textValue: TextView,
    onRatingChange: OnValueChange<Float>?
) {
    seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            val rating = progress / 10f
            textValue.text = String.format("%.1f", rating)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) = Unit

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            onRatingChange?.onChanged(seekBar.progress / 10f)
        }

    })
}

@BindingAdapter("onValueChange")
fun EditText.onValueChange(onValueChange: OnValueChange<String>) {
    onChange(onValueChange::onChanged)
}

@BindingAdapter("onNavigationClick")
fun navigationOnClick(toolbar: Toolbar, onClickListener: View.OnClickListener) {
    toolbar.setNavigationOnClickListener(onClickListener)
}
