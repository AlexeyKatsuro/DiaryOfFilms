package com.alexeykatsuro.inputfromutil

data class InputState(
    val text: String = "",
    val isValid: Boolean = true,
    val errorMessage: String? = null,
    val onTextChange: (String) -> Unit = {}
) {
    override fun toString() = text
}

data class Input(
    val text: String = "",
    val errorMessage: String? = null
)