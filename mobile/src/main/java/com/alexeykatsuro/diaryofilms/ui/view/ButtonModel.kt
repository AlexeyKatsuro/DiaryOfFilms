package com.alexeykatsuro.diaryofilms.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.FrameLayout
import androidx.annotation.DimenRes
import androidx.annotation.Dimension
import androidx.core.view.setPadding
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.util.epoxy.padding.PaddingEpx
import com.alexeykatsuro.diaryofilms.util.epoxy.padding.dpToPx
import com.alexeykatsuro.diaryofilms.util.epoxy.padding.resToPx
import com.alexeykatsuro.diaryofilms.util.epoxy.padding.setViewPadding

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ButtonModel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val button by lazy { findViewById<Button>(R.id.button) }

    init {
        inflate(context, R.layout.view_button, this)
    }

    @TextProp
    fun setText(title: CharSequence?) {
        button.text = title
    }

    @CallbackProp
    fun onClick(listener: OnClickListener?) {
        button.setOnClickListener(listener)
    }

    @ModelProp(group = "padding")
    fun setPadding(padding: PaddingEpx?) {
        setViewPadding(padding)
    }

    @ModelProp(group = "padding")
    fun setPaddingDp(@Dimension(unit = Dimension.DP) paddingDp: Int) {
        val px = context.dpToPx(paddingDp)
        setPadding(px)
    }

    @ModelProp(group = "padding")
    fun setPaddingRes(@DimenRes paddingRes: Int) {
        val px = context.resToPx(paddingRes)
        setPadding(px, px, px, px)
    }

}