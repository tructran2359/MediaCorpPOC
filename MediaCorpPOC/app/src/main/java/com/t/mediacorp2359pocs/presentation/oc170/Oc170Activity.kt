package com.t.mediacorp2359pocs.presentation.oc170

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.t.mediacorp2359pocs.R

class Oc170Activity : AppCompatActivity() {

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc170Activity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oc170)
    }
}