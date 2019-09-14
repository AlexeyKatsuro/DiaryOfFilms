package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.alexeykatsuro.data.dto.FilmRecord
import com.alexeykatsuro.data.util.extensions.observeEvent
import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.base.BindingInflater
import com.alexeykatsuro.diaryofilms.base.mvrx.DofMvRxFragment
import com.alexeykatsuro.diaryofilms.databinding.FragmentAdoptFilmBinding
import com.alexeykatsuro.diaryofilms.util.OnValueChange
import com.alexeykatsuro.diaryofilms.util.extensions.parseDate
import com.alexeykatsuro.diaryofilms.util.input.isDate
import com.alexeykatsuro.inputfromutil.Input
import com.alexeykatsuro.inputfromutil.validation.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.reflect.KProperty1

class AdoptFilmFragment :
    DofMvRxFragment<FragmentAdoptFilmBinding>() {

    private lateinit var form_: Form_<AdoptFilmState, AdoptFilmViewModel>

    @Inject
    lateinit var viewModelFactory: AdoptFilmViewModel.Factory

    private val viewModel: AdoptFilmViewModel by fragmentViewModel()
    override val inflater: BindingInflater<FragmentAdoptFilmBinding> =
        FragmentAdoptFilmBinding::inflate

    override fun invalidate() = withState(viewModel) {
        withBinding {
            state = it
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAssertions()

        withBinding {

            onNavUpClick = View.OnClickListener {
                navController.navigateUp()
            }

            onSaveClick = View.OnClickListener {
                if (form_.validate()) {
                    viewModel.adoptFilm(assembleFilmRecord())
                }
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
                viewModel.updateState { copy(title = title.copy(text = it)) }
            }

            onYearChange = OnValueChange {
                viewModel.updateState { copy(year = year.copy(text = it)) }
            }

            onWatchingDateChange = OnValueChange {
                viewModel.updateState { copy(watchingDate = watchingDate.copy(text = it)) }
            }
        }
        viewModel.selectSubscribe(viewLifecycleOwner, AdoptFilmState::title) {

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

    private fun assembleFilmRecord() = withState(viewModel) {
        FilmRecord(
            title = it.title.text,
            year = it.year.text.toInt(),
            rating = it.rating,
            subjectiveRating = it.subjectiveRating,
            watchingDate = it.watchingDate.text.parseDate(getString(R.string.date_pattern))
        )
    }

    private fun initAssertions() {
        form_ = createFrom(viewModel) { state ->
            withField(AdoptFilmState::title, afterValidate = {
                viewModel.updateState { copy(title = title.copy(errorMessage = it.messageOrNull())) }
            }) {
                isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
            }

            withField(AdoptFilmState::year, afterValidate = {
                viewModel.updateState { copy(year = year.copy(errorMessage = it.messageOrNull())) }
            }) {
                isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
                isNumber().greaterThan(1900).errorMessage(getString(R.string.error_input_invalid))
            }

            withField(AdoptFilmState::watchingDate, afterValidate = {
                viewModel.updateState { copy(watchingDate = watchingDate.copy(errorMessage = it.messageOrNull())) }
            }) {
                isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
                isDate(getString(R.string.date_pattern)).errorMessage(getString(R.string.error_input_date_pattern))
            }

        }
    }
}

fun <S : MvRxState, VM : BaseMvRxViewModel<S>> createFrom(vm: VM, initializer: Form_<S, VM>.(S) -> Unit): Form_<S, VM> {
    val from = Form_(vm)
    withState(vm) {
        from.initializer(it)
    }
    return from
}

class Form_<S : MvRxState, VM : BaseMvRxViewModel<S>>(
    private val viewModel: VM
) {

    private val mutableMap: MutableMap<KProperty1<S, Input>, InputTools> = mutableMapOf()

    fun withField(input: KProperty1<S, Input>, afterValidate: onInvalidCallback, setup: InputValidator.() -> Unit) {
        mutableMap[input] = InputTools(InputValidator().also(setup), afterValidate)
    }

    fun validate(silent: Boolean = false): Boolean {
        return withState(viewModel) { state ->
            var result = true
            mutableMap.asSequence().forEach { entry ->
                val text = entry.key.get(state).text
                val tools = entry.value
                val vresult = tools.validator.validate(text)
                if (!silent) {
                    tools.onInvalid(vresult.failureAssertions)
                }

                result = vresult.isValid && result
            }
            result
        }
    }
}

class InputTools(val validator: InputValidator, val onInvalid: onInvalidCallback)
