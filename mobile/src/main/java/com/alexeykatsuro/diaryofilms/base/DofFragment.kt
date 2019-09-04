package com.alexeykatsuro.diaryofilms.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.reflect.KClass

typealias BindingInflater<VB> = (LayoutInflater, ViewGroup?, Boolean) -> VB

abstract class  DOFFragment<VB : ViewDataBinding> : DaggerFragment() {

    protected open lateinit var binding: VB
    protected open lateinit var navController: NavController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract val inflater: BindingInflater<VB>

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = createDataBinding(inflater, container)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()
    }

    protected open fun createDataBinding(inflater: LayoutInflater, container: ViewGroup?): VB =
        inflater(inflater, container, false)

    protected inline fun withBinding(block: VB.() -> Unit) {
        binding.apply(block)
    }

}