package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.alexeykatsuro.data.dto.FilmRecord
import com.alexeykatsuro.data.util.extensions.copy
import com.alexeykatsuro.data.util.extensions.observeEvent
import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.base.BindingInflater
import com.alexeykatsuro.diaryofilms.base.mvrx.DofMvRxFragment
import com.alexeykatsuro.diaryofilms.databinding.FragmentAdoptFilmBinding
import com.alexeykatsuro.diaryofilms.util.extensions.parseDate
import com.alexeykatsuro.diaryofilms.util.input.isDate
import com.alexeykatsuro.inputfromutil.Form
import com.alexeykatsuro.inputfromutil.OnValueChange
import com.alexeykatsuro.inputfromutil.createFrom
import com.alexeykatsuro.inputfromutil.validation.assertions.errorMessage
import com.alexeykatsuro.inputfromutil.validation.isNotEmpty
import com.alexeykatsuro.inputfromutil.validation.isNumber
import timber.log.Timber
import javax.inject.Inject

class AdoptFilmFragment :
    DofMvRxFragment<FragmentAdoptFilmBinding>(), TempController.Callbacks {

    private lateinit var form_: Form<AdoptFilmState>

    @Inject
    lateinit var viewModelFactory: AdoptFilmViewModel.Factory

    private val viewModel: AdoptFilmViewModel by fragmentViewModel()
    override val inflater: BindingInflater<FragmentAdoptFilmBinding> =
        FragmentAdoptFilmBinding::inflate

    private lateinit var controller: TempController

    override fun invalidate() = withState(viewModel) {
        Timber.e("$it")
        controller.setData(it)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = TempController(this)
        initForm()

        withBinding {

            inputRv.setController(controller)

            onNavUpClick = View.OnClickListener {
                navController.navigateUp()
            }


        }


        viewModel.onFilmSaved.observeEvent(viewLifecycleOwner) {
            navController.navigateUp()
        }
    }

    override fun onItemTextChanged(index: Int, text: String) {
        viewModel.updateState {
            copy(inputs = inputs.copy(index, text))
        }
    }

    override fun onSaveClick() = View.OnClickListener {
        Timber.e("onSaveClick")
        if (form_.validate()) {
            viewModel.adoptFilm(assembleFilmRecord())
        }
    }

    override fun onRatingChange() = OnValueChange<Float> {
        Timber.e("onRatingChange : $it")
        viewModel.updateState {
            copy(rating = it)
        }
    }

    override fun onSubjectiveRatingChange() = OnValueChange<Float> {
        Timber.e("onSubjectiveRatingChange : $it")
        viewModel.updateState {
            copy(subjectiveRating = it)
        }
    }

    override fun onWatchingDateChange() = OnValueChange<String> {
        Timber.e("onWatchingDateChange : $it")
        viewModel.updateState { copy(watchingDate = it) }
    }

    override fun onTitleChange() = OnValueChange<String> {
        Timber.e("onTitleChange : $it")
        viewModel.updateState { copy(title = it) }
    }

    override fun onYearChange() = OnValueChange<String> {
        Timber.e("onYearChange : $it")
        viewModel.updateState { copy(year = it) }
    }

    private fun assembleFilmRecord() = withState(viewModel) {
        FilmRecord(
            title = it.title,
            year = it.year.toInt(),
            rating = it.rating,
            subjectiveRating = it.subjectiveRating,
            watchingDate = it.watchingDate.parseDate(getString(R.string.date_pattern))
        )
    }

    private fun initForm() {
        form_ = createFrom({ withState(viewModel) { it } }) { state ->
            withProp(AdoptFilmState::title, { copy(titleError = it) }) {
                isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
            }

            withProp(AdoptFilmState::year, { copy(yearError = it) }) {
                isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
                isNumber().greaterThan(1900)
                    .errorMessage(getString(R.string.error_input_invalid))
            }

            withProp(AdoptFilmState::watchingDate, { copy(watchingDateError = it) }) {
                isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
                isDate(getString(R.string.date_pattern))
                    .errorMessage(getString(R.string.error_input_date_pattern))
            }

            state.inputs.forEachIndexed { index, item ->
                withProp(
                    propProvider = { it.inputs[index] },
                    reducer = { copy(inputErrors = inputErrors.copy(index, it)) },
                    name = "$index"
                ) {
                    isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
                }
            }
            withProp<String>(name = "1") {
                isNumber().greaterThan(1900)
                    .errorMessage(getString(R.string.error_input_invalid))
            }
            withProp<String>("4") {
                isDate(getString(R.string.date_pattern))
                    .errorMessage(getString(R.string.error_input_date_pattern))
            }

            onStateChange { newState ->
                viewModel.updateState { newState }
            }
        }
    }
}







