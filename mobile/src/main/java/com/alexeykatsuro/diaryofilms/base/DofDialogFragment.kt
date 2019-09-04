package com.alexeykatsuro.diaryofilms.base

import android.app.AlertDialog
import android.app.Dialog
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
import com.alexeykatsuro.data.util.extensions.observeValue
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class DofDialogFragment<VB : ViewDataBinding> :
    DaggerDialogFragment() {

    protected open lateinit var binding: VB

    abstract val inflater: BindingInflater<VB>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = createDataBinding(LayoutInflater.from(requireContext()), null)
        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getViewLifecycleOwnerLiveData().observeValue(this){
            binding.lifecycleOwner = it
        }

    }

    protected open fun createDataBinding(inflater: LayoutInflater, container: ViewGroup?): VB =
        inflater(inflater, container, false)


    protected inline fun withBinding(block: VB.() -> Unit) {
        binding.apply(block)
        binding.executePendingBindings()
    }
}
