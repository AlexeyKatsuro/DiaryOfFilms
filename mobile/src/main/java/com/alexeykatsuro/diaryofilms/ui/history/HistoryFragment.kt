package com.alexeykatsuro.diaryofilms.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.afollestad.recyclical.datasource.emptyDataSourceTyped
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.alexeykatsuro.data.dto.FilmRecord
import com.alexeykatsuro.data.util.extensions.observeValue
import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.base.DOFFragment
import com.alexeykatsuro.diaryofilms.base.BindingInflater
import com.alexeykatsuro.diaryofilms.databinding.FragmentHistoryBinding
import com.alexeykatsuro.diaryofilms.ui.adoptfilm.AdoptFilmFragment
import com.alexeykatsuro.diaryofilms.util.extensions.addDividerItemDecoration

class HistoryFragment : DOFFragment<FragmentHistoryBinding>() {

    override val inflater: BindingInflater<FragmentHistoryBinding> =
        FragmentHistoryBinding::inflate

    private val viewModel by viewModels<HistoryViewModel> { viewModelFactory }

    private val historyDataSource = emptyDataSourceTyped<FilmRecord>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        withBinding {
            historyRecycler.setup {
                recyclerView.addDividerItemDecoration()

                withDataSource(historyDataSource)
                withItem<FilmRecord, HistoryItemHolder>(R.layout.item_film_record) {
                    onBind(::HistoryItemHolder) { _, item ->
                        bind(item)
                    }
                }
            }

            setOnAddFilmClick {
                showAdoptDialog()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.allFilms.observeValue(viewLifecycleOwner) {
            historyDataSource.set(
                newItems = it,
                areTheSame = { left, right -> left.id == right.id },
                areContentsTheSame = { left, right -> left == right })
        }
    }

    private fun showAdoptDialog() {
        val dialog = AdoptFilmFragment.newInstance()
        dialog.setTargetFragment(this, 0)
        dialog.show(requireFragmentManager(), "AdoptFilmFragment")
    }
}