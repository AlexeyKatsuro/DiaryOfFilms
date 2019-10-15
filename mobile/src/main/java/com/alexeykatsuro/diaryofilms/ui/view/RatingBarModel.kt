package com.alexeykatsuro.diaryofilms.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.annotation.FloatRange
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.alexeykatsuro.diaryofilms.databinding.ViewRatingBarBinding
import com.alexeykatsuro.inputfromutil.OnValueChange

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class RatingBarModel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    private val binding: ViewRatingBarBinding =
        ViewRatingBarBinding.inflate(LayoutInflater.from(context), this, true)

    @TextProp
    fun setTitle(title: CharSequence?) {
        binding.textTitle.text = title
    }

    @ModelProp
    fun setRating(@FloatRange(from = 0.0, to = 10.0) rating: Float) {
        binding.textValue.text = String.format("%.1f", rating)
    }

    @CallbackProp
    fun setOnRatingChange(onRatingChange: OnValueChange<Float>?) {
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val rating = progress / 10f
                binding.textValue.text = String.format("%.1f", rating)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) = Unit

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                onRatingChange?.onChanged(seekBar.progress / 10f)
            }

        })
    }
}