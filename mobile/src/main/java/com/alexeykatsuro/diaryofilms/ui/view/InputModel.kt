package com.alexeykatsuro.diaryofilms.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.DimenRes
import androidx.annotation.Dimension
import androidx.core.view.setPadding
import com.airbnb.epoxy.*
import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.util.epoxy.padding.PaddingEpx
import com.alexeykatsuro.diaryofilms.util.epoxy.padding.dpToPx
import com.alexeykatsuro.diaryofilms.util.epoxy.padding.resToPx
import com.alexeykatsuro.diaryofilms.util.epoxy.padding.setViewPadding
import com.alexeykatsuro.diaryofilms.util.setInput
import com.alexeykatsuro.inputfromutil.OnValueChange
import com.google.android.material.textfield.TextInputLayout


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class InputModel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val inputView by lazy { findViewById<TextInputLayout>(R.id.input) }

    @set:TextProp
    var text: CharSequence? = null

    @set:CallbackProp
    var onChange: OnValueChange<String>? = null

    init {
        inflate(context, R.layout.view_input, this)
    }


    @JvmOverloads
    @TextProp
    fun setHint(hint: CharSequence? = null) {
        inputView.hint = hint
    }

    @JvmOverloads
    @TextProp
    fun setError(error: CharSequence? = null) {
        inputView.error = error
    }

    @JvmOverloads
    @ModelProp
    fun errorEnabled(isEnabled: Boolean = false) {
        inputView.isErrorEnabled = isEnabled
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


    @AfterPropsSet
    fun update() {
        inputView.editText?.setInput(text, onChange)
    }

}