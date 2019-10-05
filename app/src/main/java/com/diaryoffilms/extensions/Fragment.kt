package com.diaryoffilms.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

/**
 * Find a [NavController] given a [Fragment]
 *
 * Calling this on a Fragment that is not a [NavHostFragment] or within a [NavHostFragment]
 * will result in an [IllegalStateException]
 */
fun Fragment.findNavController(): NavController =
        NavHostFragment.findNavController(this)

fun Fragment.dismissKeyboard() {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    val windowToken = view?.findFocus()?.windowToken
    windowToken?.let { imm?.hideSoftInputFromWindow(it, 0) }
            ?: imm?.hideSoftInputFromWindow(view?.rootView?.windowToken, 0)
}

fun Fragment.setSupportActionBar(toolbar: Toolbar){
    (requireActivity() as? AppCompatActivity)?.setSupportActionBar(toolbar)
}

fun Fragment.getSupportActionBar() =
    (requireActivity() as? AppCompatActivity)?.supportActionBar