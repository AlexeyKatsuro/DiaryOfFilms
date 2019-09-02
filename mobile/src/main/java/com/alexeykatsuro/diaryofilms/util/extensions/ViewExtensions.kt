package com.alexeykatsuro.diaryofilms.util.extensions

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addDividerItemDecoration() {
    val lm = layoutManager as LinearLayoutManager
    val dividerItemDecoration = DividerItemDecoration(context, lm.orientation)
    addItemDecoration(dividerItemDecoration)
}

fun View.setAnimatedVisibility(visibility: Int) {
    val visible = visibility == View.VISIBLE
    val targetAlpha = if (visible) 1f else 0f
    this.visibility = View.VISIBLE
    val anim = animate()
    anim.cancel()
    anim.alpha(targetAlpha)
    if (!visible) {
        anim.withEndAction {
            this@setAnimatedVisibility.visibility = visibility
        }
    }
}