package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.base.BaseBottomSheetDialogFragment
import com.alexeykatsuro.diaryofilms.base.BindingInflater
import com.alexeykatsuro.diaryofilms.data.dto.FilmRecord
import com.alexeykatsuro.diaryofilms.databinding.FragmentAdoptFilmBinding
import com.alexeykatsuro.diaryofilms.util.extensions.observeEvent
import com.alexeykatsuro.diaryofilms.util.extensions.parseDate
import com.alexeykatsuro.diaryofilms.util.extensions.text
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
            setOnSaveClick {
                viewModel.adoptFilm(assebleFimlRecord())
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.onFilmSaved.observeEvent(viewLifecycleOwner){
            dismiss()
        }
    }

    fun assebleFimlRecord(): FilmRecord {
        return withBinding {
            FilmRecord(
                title = textInputName.text,
                year = textInputYear.text.toInt(),
                rating = textInputRating.text.toFloat(),
                subjectiveRating = textInputSubjectiveRating.text.toFloat(),
                watchingDate = textInputWatchingDate.text.parseDate(getString(R.string.date_pattern))
            )
        }
    }
}
