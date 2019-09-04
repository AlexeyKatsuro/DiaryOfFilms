package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.alexeykatsuro.data.dto.FilmRecord
import com.alexeykatsuro.data.util.extensions.observeEvent
import com.alexeykatsuro.data.util.extensions.observeValue
import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.base.DofBottomSheetDialogFragment
import com.alexeykatsuro.diaryofilms.base.BindingInflater
import com.alexeykatsuro.diaryofilms.databinding.FragmentAdoptFilmBinding
import com.alexeykatsuro.diaryofilms.util.extensions.executeAfter
import com.alexeykatsuro.diaryofilms.util.extensions.parseDate
import com.alexeykatsuro.diaryofilms.util.input.isDate

class AdoptFilmFragment :
    DofBottomSheetDialogFragment<FragmentAdoptFilmBinding>() {

    companion object {
        fun newInstance() = AdoptFilmFragment().apply {
            arguments = bundleOf()
        }
    }

    val viewModel by viewModels<AdoptFilmViewModel> { viewModelFactory }
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
                isNotEmpty()
                    .errorMessage = getString(R.string.error_input_is_empty)
            }

            year.setupAssertions {
                isNumber()
                    .greaterThan(1900)
                    .errorMessage = getString(R.string.error_input_invalid)
                length()
                    .exactly(4)
                    .errorMessage = getString(R.string.error_input_invalid)
            }

            watchingDate.setupAssertions {
                isDate(getString(R.string.date_pattern))
                    .errorMessage = getString(R.string.error_input_date_pattern)
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
