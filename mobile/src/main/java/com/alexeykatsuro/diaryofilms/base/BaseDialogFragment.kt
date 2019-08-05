package com.alexeykatsuro.diaryofilms.base

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseDialogFragment<VB : ViewDataBinding, VM : BaseViewModel> :
    DaggerDialogFragment() {

    protected open lateinit var binding: VB
    protected open lateinit var viewModel: VM
    protected open lateinit var navController: NavController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract val viewModelClass: KClass<VM>
    abstract val layoutId: Int

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = createDataBinding(LayoutInflater.from(requireContext()), null)
        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = crateViewModel(viewModelFactory, viewModelClass)
    }


    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        navController = findNavController()
    }

    protected open fun createDataBinding(inflater: LayoutInflater, container: ViewGroup?): VB =
        DataBindingUtil.inflate(inflater, layoutId, container, false)

    protected open fun crateViewModel(provider: ViewModelProvider.Factory, clazz: KClass<VM>): VM =
        ViewModelProviders.of(this, provider).get(clazz.java)

    protected inline fun withBinding(block: VB.() -> Unit) {
        binding.apply(block)
    }
}
