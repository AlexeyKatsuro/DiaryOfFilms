package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.alexeykatsuro.data.dto.FilmRecord
import com.alexeykatsuro.data.util.extensions.observeEvent
import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.base.BindingInflater
import com.alexeykatsuro.diaryofilms.base.mvrx.DofMvRxDialogFragment
import com.alexeykatsuro.diaryofilms.base.mvrx.DofMvRxFragment
import com.alexeykatsuro.diaryofilms.databinding.FragmentAdoptFilmBinding
import com.alexeykatsuro.diaryofilms.util.OnValueChange
import com.alexeykatsuro.diaryofilms.util.extensions.parseDate
import timber.log.Timber
import javax.inject.Inject

class AdoptFilmFragment :
    DofMvRxFragment<FragmentAdoptFilmBinding>() {

    @Inject
    lateinit var viewModelFactory: AdoptFilmViewModel.Factory

    private val viewModel: AdoptFilmViewModel by fragmentViewModel()
    override val inflater: BindingInflater<FragmentAdoptFilmBinding> =
        FragmentAdoptFilmBinding::inflate

    override fun invalidate() = withState(viewModel) {
        withBinding {
            Timber.e("State: $it")
            state = it
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        withBinding {

            onNavUpClick = View.OnClickListener {
                navController.navigateUp()
            }

            onSaveClick = View.OnClickListener {
                viewModel.adoptFilm(assembleFilmRecord())
            }
            onRatingChange = OnValueChange {
                viewModel.updateState {
                    copy(rating = it)
                }
            }

            onSubjectiveRatingChange = OnValueChange {
                viewModel.updateState {
                    copy(subjectiveRating = it)
                }
            }

            onTitleChange = OnValueChange {
                viewModel.updateState { copy(title = it) }
            }

            onYearChange = OnValueChange {
                viewModel.updateState { copy(year = it) }
            }

            onWatchingDateChange = OnValueChange {
                viewModel.updateState { copy(watchingDate = it) }
            }
        }
        viewModel.onFilmSaved.observeEvent(viewLifecycleOwner) {
            navController.navigateUp()
        }

        /*viewModel.inputForm.run {
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
        }*/
    }

      private fun assembleFilmRecord() = withState(viewModel){
          FilmRecord(
              title = it.title,
              year = it.year.toInt(),
              rating = it.rating,
              subjectiveRating = it.subjectiveRating,
              watchingDate = it.watchingDate.parseDate(getString(R.string.date_pattern))
          )
      }
}
