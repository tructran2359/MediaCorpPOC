package com.t.mediacorp2359pocs.presentation.oc813

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.t.mediacorp2359pocs.databinding.ActivityOc813SignUpBinding

class Oc813SignUpActivity : AppCompatActivity() {

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc813SignUpActivity::class.java)
        }
    }

    private lateinit var mBinding: ActivityOc813SignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityOc813SignUpBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }
}