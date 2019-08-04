package com.alexeykatsuro.diaryofilms.ui

import android.os.Bundle
import com.alexeykatsuro.diaryofilms.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
