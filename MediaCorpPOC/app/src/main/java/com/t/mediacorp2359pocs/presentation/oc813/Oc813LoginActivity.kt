package com.t.mediacorp2359pocs.presentation.oc813

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.t.mediacorp2359pocs.databinding.ActivityOc813LoginBinding

class Oc813LoginActivity : AppCompatActivity() {

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc813LoginActivity::class.java)
        }
    }

    private lateinit var mBiding: ActivityOc813LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBiding = ActivityOc813LoginBinding.inflate(layoutInflater)
        setContentView(mBiding.root)
    }
}