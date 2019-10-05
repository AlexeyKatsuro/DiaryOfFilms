package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import com.airbnb.epoxy.TypedEpoxyController
import com.alexeykatsuro.diaryofilms.input
import com.alexeykatsuro.inputfromutil.OnValueChange

class TempController : TypedEpoxyController<List<String>>() {

    private var callbacks: Callbacks? = null
    override fun buildModels(data: List<String>?) {
        if (data == null) return

        data.forEachIndexed { index, s ->
            input {
                id("input$index")
                text(s)
                onTextChange(OnValueChange {
                    if (it != s) {
                        callbacks?.onItemTextChanged(index, it)
                    }
                })
            }
        }
    }

    fun setCallBacks(callbacks: Callbacks?) {
        this.callbacks = callbacks
    }


    interface Callbacks {
        fun onItemTextChanged(index: Int, text: String)
    }
}