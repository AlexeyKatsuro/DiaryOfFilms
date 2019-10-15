package com.alexeykatsuro.diaryofilms.ui.view

import android.content.Context
import android.util.AttributeSet
import com.airbnb.epoxy.EpoxyRecyclerView

class BarrenRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : EpoxyRecyclerView(context, attrs, defStyleAttr) {

    //Block sharing the same view pool across all EpoxyRecyclerVie
    override fun shouldShareViewPoolAcrossContext(): Boolean {
        return false
    }
}
