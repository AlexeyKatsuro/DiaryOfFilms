package com.alexeykatsuro.diaryofilms.base

import android.content.Context
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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseBottomSheetDialogFragment<VB : ViewDataBinding, VM : BaseViewModel> :
    BottomSheetDialogFragment(), HasAndroidInjector {

    protected open lateinit var binding: VB
    protected open lateinit var viewModel: VM
    protected open lateinit var navController: NavController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract val viewModelClass: KClass<VM>
    abstract val inflater: BindingInflater<VB>

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel(viewModelFactory, viewModelClass)
    }

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

    protected open fun createViewModel(provider: ViewModelProvider.Factory, clazz: KClass<VM>): VM =
        ViewModelProviders.of(this, provider).get(clazz.java)

    protected inline fun <T> withBinding(block: VB.() -> T): T {
        return binding.block()
    }
}
