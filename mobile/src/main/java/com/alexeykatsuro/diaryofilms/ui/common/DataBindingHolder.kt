package com.alexeykatsuro.diaryofilms.ui.common

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
typealias ViewBinder<VB> = (itemView: View) -> VB

abstract class DataBindingHolder<VB : ViewDataBinding, IT: Any>(
    itemView: View, binder: ViewBinder<VB>) :
    RecyclerView.ViewHolder(itemView) {

    protected val binding: VB = binder(itemView)

    abstract fun bind(item: IT)
}