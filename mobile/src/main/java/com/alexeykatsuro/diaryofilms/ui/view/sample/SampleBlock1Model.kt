package com.alexeykatsuro.diaryofilms.ui.view.sample

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.alexeykatsuro.diaryofilms.databinding.ViewSampleBlock1Binding
import com.alexeykatsuro.inputfromutil.cover.InputCover
import com.alexeykatsuro.inputfromutil.cover.RadioCover
import com.alexeykatsuro.inputfromutil.cover.applyCover
import com.alexeykatsuro.inputfromutil.updateError
import timber.log.Timber

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
        Timber.e("setContractNumber")
        binding.inputContractNumber.applyCover(cover)
    }

    @JvmOverloads
    @TextProp
    fun setContractNumberError(message: CharSequence? = null) {
        binding.inputContractNumber.updateError(message)
    }

    @ModelProp
    fun setContractDate(cover: InputCover?) {
        Timber.e("setContractDate")
        binding.inputContractDate.applyCover(cover)
    }

    @JvmOverloads
    @TextProp
    fun setContractDateError(message: CharSequence? = null) {
        binding.inputContractDate.updateError(message)
    }

    @ModelProp
    fun setAmount(cover: InputCover?) {
        Timber.e("setAmount")
        binding.inputLcAmount.applyCover(cover)
    }

    @JvmOverloads
    @TextProp
    fun setAmountError(message: CharSequence? = null) {
        binding.inputLcAmount.updateError(message)
    }

    @ModelProp
    fun setCurrency(cover: InputCover?) {
        Timber.e("setCurrency")
        binding.inputCurrency.applyCover(cover)
    }

    @JvmOverloads
    @TextProp
    fun setCurrencyError(message: CharSequence? = null) {
        binding.inputCurrency.updateError(message)
    }

    @ModelProp
    fun isAccountBuyer(cover: RadioCover?) {
        Timber.e("isAccountBuyer")
        binding.buttonBuyer.applyCover(cover)
    }

    @ModelProp
    fun isAccountSeller(cover: RadioCover?) {
        Timber.e("isAccountSeller")
        binding.buttonSeller.applyCover(cover)
    }

    @JvmOverloads
    @TextProp
    fun accountError(error: CharSequence? = null) {
        binding.textError.isGone = error == null
        binding.textError.text = error
    }
}