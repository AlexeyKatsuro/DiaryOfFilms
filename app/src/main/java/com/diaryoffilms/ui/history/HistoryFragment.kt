package com.diaryoffilms.ui.history

import com.diaryoffilms.R
import com.diaryoffilms.base.BaseFragment
import com.diaryoffilms.databinding.FragmentHistoryBinding
import kotlin.reflect.KClass

class HistoryFragment : BaseFragment<FragmentHistoryBinding, HistoryViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_history
    override val viewModelClass: KClass<HistoryViewModel>
        get() = HistoryViewModel::class

}