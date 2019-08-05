package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import androidx.core.os.bundleOf
import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.base.BaseDialogFragment
import com.alexeykatsuro.diaryofilms.databinding.FragmentAdoptFilmBinding
import kotlin.reflect.KClass

class AdoptFilmFragment : BaseDialogFragment<FragmentAdoptFilmBinding, AdoptFilmViewModel>() {

    companion object {
        fun newInstance() = AdoptFilmFragment().apply {
            arguments = bundleOf()
        }
    }

    override val viewModelClass: KClass<AdoptFilmViewModel> = AdoptFilmViewModel::class
    override val layoutId: Int = R.layout.fragment_adopt_film
}
