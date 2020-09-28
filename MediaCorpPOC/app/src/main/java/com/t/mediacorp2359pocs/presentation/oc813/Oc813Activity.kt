package com.t.mediacorp2359pocs.presentation.oc813

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.t.mediacorp2359pocs.databinding.Activity813Binding

class Oc813Activity : AppCompatActivity() {

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc813Activity::class.java)
        }
    }

    private lateinit var mBinding: Activity813Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = Activity813Binding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setUpViews()
    }

    private fun setUpViews() {

        mBinding.btnLogin.setOnClickListener {
            startActivity(Oc813LoginActivity.getLaunchIntent(this))
        }

        mBinding.btnSignUp.setOnClickListener {
            startActivity(Oc813SignUpActivity.getLaunchIntent(this))
        }
    }

}