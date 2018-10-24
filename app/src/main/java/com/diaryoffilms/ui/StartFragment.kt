package com.diaryoffilms.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diaryoffilms.databinding.FragmentStartBinding

class StartFragment: Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentStartBinding.inflate(inflater,container,false)

        return binding.root
    }
}