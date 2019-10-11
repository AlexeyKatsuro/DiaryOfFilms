package com.alexeykatsuro.diaryofilms.ui.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.afollestad.recyclical.datasource.emptyDataSourceTyped
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.alexeykatsuro.data.dto.FilmRecord
import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.base.BindingInflater
import com.alexeykatsuro.diaryofilms.base.mvrx.DofMvRxFragment
import com.alexeykatsuro.diaryofilms.databinding.FragmentHistoryBinding
import com.alexeykatsuro.diaryofilms.ui.adoptfilm.AdoptFilmFragment
import com.alexeykatsuro.diaryofilms.util.extensions.addDividerItemDecoration
import com.alexeykatsuro.diaryofilms.util.extensions.parseDate
import javax.inject.Inject

class HistoryFragment : DofMvRxFragment<FragmentHistoryBinding>() {

    override val inflater: BindingInflater<FragmentHistoryBinding> =
        FragmentHistoryBinding::inflate

    @Inject
    lateinit var historyViewModelFactory: HistoryViewModel.Factory
    private val viewModel: HistoryViewModel by fragmentViewModel()

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
                //val direction = HistoryFragmentDirections.showAdoptFilmFragment()
                val direction = HistoryFragmentDirections.showSampleFragment()
                navController.navigate(direction)
            }
        }
    }

    override fun invalidate() = withState(viewModel) {
        historyDataSource.set(
            newItems = it.films,
            areTheSame = { left, right -> left.id == right.id },
            areContentsTheSame = { left, right -> left == right })
    }

}