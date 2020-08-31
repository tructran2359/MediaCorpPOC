package com.t.mediacorp2359pocs.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.presentation.oc170.Oc170Activity
import com.t.mediacorp2359pocs.presentation.oc171.Oc171Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViews()
    }

    private fun setUpViews() {
        btnOc170.setOnClickListener {
            startActivity(Oc170Activity.getLaunchIntent(this))
        }

        btnOc171.setOnClickListener {
            startActivity(Oc171Activity.getLaunchIntent(this))
        }
    }
}