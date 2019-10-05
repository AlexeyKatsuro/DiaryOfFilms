package com.alexeykatsuro.diaryofilms.ui.adoptfilm

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import com.alexeykatsuro.diaryofilms.filmInfoBlock
import com.alexeykatsuro.diaryofilms.input
import com.alexeykatsuro.inputfromutil.OnValueChange

class TempController(val callbacks: Callbacks) : TypedEpoxyController<AdoptFilmState>() {

    override fun buildModels(data: AdoptFilmState?) {
        if (data == null) return

        filmInfoBlock {
            id("filmInfoBlock")
            state(data)
            onRatingChange(callbacks.onRatingChange())
            onSubjectiveRatingChange(callbacks.onSubjectiveRatingChange())
            onTitleChange(callbacks.onTitleChange())
            onYearChange(callbacks.onYearChange())
            onWatchingDateChange(callbacks.onWatchingDateChange())
            onSaveClick(callbacks.onSaveClick())
        }

        data.inputs.forEachIndexed { index, s ->
            input {
                id("input$index")
                text(s)
                onTextChange(OnValueChange {
                    if (it != s) {
                        callbacks.onItemTextChanged(index, it)
                    }
                })
            }
        }
    }

    interface Callbacks {
        fun onItemTextChanged(index: Int, text: String)
        fun onSaveClick(): View.OnClickListener
        fun onRatingChange(): OnValueChange<Float>
        fun onSubjectiveRatingChange(): OnValueChange<Float>
        fun onWatchingDateChange(): OnValueChange<String>
        fun onTitleChange(): OnValueChange<String>
        fun onYearChange(): OnValueChange<String>

    }
}