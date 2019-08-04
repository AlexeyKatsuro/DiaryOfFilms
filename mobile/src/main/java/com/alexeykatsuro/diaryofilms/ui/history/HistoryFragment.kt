package com.alexeykatsuro.diaryofilms.ui.history

import android.os.Bundle
import android.view.View
import com.afollestad.recyclical.datasource.emptyDataSourceTyped
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.base.BaseFragment
import com.alexeykatsuro.diaryofilms.data.dto.FilmRecord
import com.alexeykatsuro.diaryofilms.databinding.FragmentHistoryBinding
import com.alexeykatsuro.diaryofilms.util.extensions.addDividerItemDecoration
import java.util.*
import kotlin.reflect.KClass

class HistoryFragment : BaseFragment<FragmentHistoryBinding, HistoryViewModel>() {

    override val layoutId: Int = R.layout.fragment_history
    override val viewModelClass: KClass<HistoryViewModel> = HistoryViewModel::class

    private val historyDataSource = emptyDataSourceTyped<FilmRecord>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        withBinding {
            historyRecycler.setup {
                recyclerView.addDividerItemDecoration()

                withDataSource(historyDataSource)
                withItem<FilmRecord, HistoryItemHolder>(R.layout.item_film_record) {
                    onBind(::HistoryItemHolder) { index, item ->
                        bind(item)
                    }
                }
            }
        }

        repeat(150) {
            historyDataSource.add(getFilm(it.toLong()))
        }
    }

    fun getFilm(id: Long) =
        FilmRecord(
            id, "Интерстеллар", 2015, 8.0f, 7.5f, Date()
        )

}