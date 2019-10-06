package com.alexeykatsuro.inputfromutil

import com.alexeykatsuro.inputfromutil.validation.Approver
import com.alexeykatsuro.inputfromutil.validation.ValidResult

class InputField internal constructor(default: String, private val onStateChanged: OnStateChanged<InputState>) {

    private val inputValidator = Approver()

    private var _state = InputState(default, onTextChange = {
        updateInputState {
            copy(text = it)
        }
    })

    val state: InputState
        get() = _state

    var text: String
        get() = state.text
        set(value) {
            if (value != state.text) {
                updateInputState {
                    copy(text = value)
                }
            }
        }

    fun validate(silent: Boolean = false): ValidResult {
        val result = inputValidator.verify(state.text)
        if (!silent) {
            updateInputState {
                copy(
                    isValid = result.isValid,
                    errorMessage = result.errorMessage
                )
            }
        }
        return result
    }

    fun setupAssertions(setup: Approver.() -> Unit) {
        inputValidator.setup()
    }

    private fun updateInputState(copyState: InputState.() -> InputState) {
        _state = state.copyState()
        onStateChanged(_state)
    }

}