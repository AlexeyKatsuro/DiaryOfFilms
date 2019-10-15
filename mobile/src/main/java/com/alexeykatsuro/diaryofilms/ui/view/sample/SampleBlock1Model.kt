package com.alexeykatsuro.diaryofilms.ui.view.sample

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.alexeykatsuro.diaryofilms.databinding.ViewSampleBlock1Binding
import com.alexeykatsuro.inputfromutil.cover.InputCover
import com.alexeykatsuro.inputfromutil.cover.RadioCover
import com.alexeykatsuro.inputfromutil.cover.applyCover

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class SampleBlock1Model @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    private val binding: ViewSampleBlock1Binding =
        ViewSampleBlock1Binding.inflate(LayoutInflater.from(context), this, true)

    @ModelProp
    fun setContractNumber(cover: InputCover?) {
        binding.inputContractNumber.applyCover(cover)
    }

    @ModelProp
    fun setContractDate(cover: InputCover?) {
        binding.inputContractDate.applyCover(cover)
    }

    @ModelProp
    fun setAmount(cover: InputCover?) {
        binding.inputLcAmount.applyCover(cover)
    }

    @ModelProp
    fun setCurrency(cover: InputCover?) {
        binding.inputCurrency.applyCover(cover)
    }

    @ModelProp
    fun isAccountBuyer(cover: RadioCover?) {
        binding.buttonBuyer.applyCover(cover)
    }

    @ModelProp
    fun isAccountSeller(cover: RadioCover?) {
        binding.buttonSeller.applyCover(cover)
    }

    @JvmOverloads
    @TextProp
    fun accountError(error: CharSequence? = null) {
        binding.textError.isGone = error == null
        binding.textError.text = error
    }
}