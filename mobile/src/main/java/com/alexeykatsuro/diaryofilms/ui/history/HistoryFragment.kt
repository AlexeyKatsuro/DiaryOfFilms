package com.alexeykatsuro.diaryofilms.ui.history

import com.alexeykatsuro.diaryofilms.R
import com.alexeykatsuro.diaryofilms.base.BaseFragment
import com.alexeykatsuro.diaryofilms.databinding.FragmentHistoryBinding
import kotlin.reflect.KClass

class HistoryFragment : BaseFragment<FragmentHistoryBinding, HistoryViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_history
    override val viewModelClass: KClass<HistoryViewModel>
        get() = HistoryViewModel::class

}