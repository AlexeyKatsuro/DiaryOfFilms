package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.base.BaseBottomSheetDialogFragment
import com.alexeykatsuro.diaryofilms.base.BindingInflater
import com.alexeykatsuro.diaryofilms.data.dto.FilmRecord
import com.alexeykatsuro.diaryofilms.databinding.FragmentAdoptFilmBinding
import com.alexeykatsuro.diaryofilms.util.extensions.executeAfter
import com.alexeykatsuro.diaryofilms.util.extensions.observeEvent
import com.alexeykatsuro.diaryofilms.util.extensions.observeValue
import com.alexeykatsuro.diaryofilms.util.extensions.parseDate
import kotlin.reflect.KClass

class AdoptFilmFragment :
    BaseBottomSheetDialogFragment<FragmentAdoptFilmBinding, AdoptFilmViewModel>() {

    companion object {
        fun newInstance() = AdoptFilmFragment().apply {
            arguments = bundleOf()
        }
    }

    override val viewModelClass: KClass<AdoptFilmViewModel> = AdoptFilmViewModel::class
    override val inflater: BindingInflater<FragmentAdoptFilmBinding> =
        FragmentAdoptFilmBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        withBinding {
            state = viewModel.inputForm.state
            setOnSaveClick {
                if (viewModel.inputForm.validate()) {
                    //viewModel.adoptFilm(assembleFilmRecord())
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.onFilmSaved.observeEvent(viewLifecycleOwner) {
            dismiss()
        }

        viewModel.inputForm.run {

            setupAssertions {
                isNotEmpty().errorMessage = getString(R.string.error_input_is_empty)
            }
            year.setupAssertions {
                isNumber().greaterThan(1900)
                    .errorMessage = getString(R.string.error_input_invalid)
                length()
                    .exactly(4)
                    .errorMessage = getString(R.string.error_input_invalid)
            }
        }

        viewModel.onStateChanged.observeValue(viewLifecycleOwner) {
            withBinding {
                executeAfter {
                    state = it
                }
            }
        }
    }

    private fun assembleFilmRecord(): FilmRecord {
        return with(viewModel.inputForm.state) {
            FilmRecord(
                title = title.text,
                year = year.text.toInt(),
                rating = rating.text.toFloat(),
                subjectiveRating = subjectiveRating.text.toFloat(),
                watchingDate = watchingDate.text.parseDate(getString(R.string.date_pattern))
            )
        }
    }
}
