package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import android.os.Bundle
import android.view.View
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxState
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
import com.alexeykatsuro.inputfromutil.OnStateChanged
import com.alexeykatsuro.inputfromutil.OnValueChange
import com.alexeykatsuro.inputfromutil.validation.*
import timber.log.Timber
import javax.inject.Inject

class AdoptFilmFragment :
    DofMvRxFragment<FragmentAdoptFilmBinding>(), TempController.Callbacks {

    private lateinit var form_: Form_<AdoptFilmState, AdoptFilmViewModel>

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
        form_ = createFrom(viewModel) { state ->
            withField(AdoptFilmState::title, { copy(titleError = it) }) {
                isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
            }

            withField(AdoptFilmState::year, { copy(yearError = it) }) {
                isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
                isNumber().greaterThan(1900)
                    .errorMessage(getString(R.string.error_input_invalid))
            }

            withField(AdoptFilmState::watchingDate, { copy(watchingDateError = it) }) {
                isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
                isDate(getString(R.string.date_pattern))
                    .errorMessage(getString(R.string.error_input_date_pattern))
            }

            state.inputs.forEachIndexed{ index , item ->
                withField({ it.inputs[index] }, { copy(inputErrors = inputErrors.copy(index, it)) }) {
                    isNotEmpty().errorMessage(getString(R.string.error_input_is_empty))
                }
            }

            onStateChange { newState ->
                viewModel.updateState { newState }
            }
        }
    }
}

fun <S : MvRxState, VM : BaseMvRxViewModel<S>> createFrom(
    vm: VM,
    initializer: Form_<S, VM>.(S) -> Unit
): Form_<S, VM> {
    val from = Form_(vm)
    withState(vm) {
        from.initializer(it)
    }
    return from
}
typealias ErrorReducer <S> = S.(error: String?) -> S

class Form_<S : MvRxState, VM : BaseMvRxViewModel<S>>(
    private val viewModel: VM
) {

    private val mutableMap: MutableMap<(S) -> String, InputTools<S>> = mutableMapOf()
    private var onStateChanged: OnStateChanged<S>? = null

    fun withField(
        input: (S) -> String,
        reducer: ErrorReducer<S>,
        setup: Approver.() -> Unit
    ) {
        mutableMap[input] = InputTools(Approver().also(setup), reducer)
    }

    fun onStateChange(onChanged: OnStateChanged<S>) {
        onStateChanged = onChanged
    }

    fun validate(silent: Boolean = false): Boolean {
        var updatedState: S? = null
        return withState(viewModel) { state ->
            var result = true
            mutableMap.asSequence().forEach { entry ->
                val propertyValue = entry.key.invoke(state)
                val tools = entry.value
                val vresult: ValidResult = tools.approver.verify(propertyValue)
                val errorMessage = vresult.errorMessage
                if (!silent) {
                    val reducer = tools.reducer
                    updatedState = (updatedState ?: state).reducer(errorMessage)
                }

                result = vresult.isValid && result
            }

            updatedState?.let {
                onStateChanged?.invoke(it)
                    ?: throw IllegalStateException("Doesn't assigned 'onStateChange' behavior")
            }
            result
        }
    }
}

class InputTools<S>(val approver: Approver, val reducer: ErrorReducer<S>)
