package com.diaryoffilms.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.diaryoffilms.R
import com.diaryoffilms.databinding.FragmentStartBinding
import com.diaryoffilms.extensions.setSupportActionBar
import kotlinx.android.synthetic.main.include_appbar.view.*

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = FragmentStartBinding.inflate(inflater, container, false)
        val toolbar = binding.fragmentStartCoordinatorLayout.toolbar
        setSupportActionBar(toolbar)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }
}