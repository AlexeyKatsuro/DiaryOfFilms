package com.diaryoffilms.ui.test

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.diaryoffilms.R
import com.diaryoffilms.data.FilmAttr
import com.diaryoffilms.databinding.FragmentStartBinding
import com.diaryoffilms.extensions.setSupportActionBar
import kotlinx.android.synthetic.main.include_appbar.view.*
import org.jetbrains.anko.toast

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    private lateinit var adapter: FilmAttrAdapter

    private val filmsList: MutableList<FilmAttr> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = FragmentStartBinding.inflate(inflater, container, false)
        val toolbar = binding.fragmentStartCoordinatorLayout.toolbar
        setSupportActionBar(toolbar)

        adapter = FilmAttrAdapter {
           requireActivity().toast("$it")
        }
        binding.filmsRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_add -> {
                filmsList.add(FilmAttr("Название","Дата", "Рейтинг"))
                adapter.submitList(filmsList)
                adapter.notifyDataSetChanged()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }
}