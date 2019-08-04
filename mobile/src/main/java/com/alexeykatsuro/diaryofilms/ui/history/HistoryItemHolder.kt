package com.alexeykatsuro.diaryofilms.ui.history

import android.view.View
import com.alexeykatsuro.diaryofilms.data.dto.FilmRecord
import com.alexeykatsuro.diaryofilms.databinding.ItemFilmRecordBinding
import com.alexeykatsuro.diaryofilms.ui.common.DataBindingHolder

class HistoryItemHolder(itemVIew: View) :
    DataBindingHolder<ItemFilmRecordBinding, FilmRecord>(itemVIew, ItemFilmRecordBinding::bind) {
    override fun bind(item: FilmRecord) {
        binding.item = item
    }
}