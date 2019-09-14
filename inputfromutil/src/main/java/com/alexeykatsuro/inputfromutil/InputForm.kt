package com.alexeykatsuro.inputfromutil

import com.alexeykatsuro.inputfromutil.validation.InputValidator


typealias OnStateChanged<T> = (newState: T) -> Unit
typealias CopyState<T> = T.(newInput: InputState) -> T

abstract class InputForm<State : Any> {
    protected var _formState: State? = null
    protected var stateChangedCallback: OnStateChanged<State>? = null
    protected val fromFields = mutableListOf<InputField>()

    val state: State
        get() = _formState ?: createState().also { _formState = it }


    fun inputField(default: String = "", copy: CopyState<State>): InputField {
        val field = InputField(default) { newInput ->
            _formState = state.copy(newInput)
            stateChangedCallback?.invoke(state)
        }
        fromFields.add(field)
        return field
    }

    fun onStateChanged(block: OnStateChanged<State>) {
        stateChangedCallback = block
    }

    fun validate(silent: Boolean = false): Boolean {
        var result = true
        for (field in fromFields) {
            result = field.validate(silent).isValid && result
        }
        return result
    }

    abstract fun createState(): State

    fun setupAssertions(setup: InputValidator.() -> Unit) {
        fromFields.forEach {
            it.setupAssertions(setup)
        }
    }
}

fun <S : Any, IF : InputForm<S>, Creator : () -> IF> createForm(
    creator: Creator,
    initializer: IF.() -> Unit
): IF {
    return creator().apply(initializer)
}