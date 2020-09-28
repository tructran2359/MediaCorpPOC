package com.t.mediacorp2359pocs.presentation.oc813

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.t.mediacorp2359pocs.databinding.ActivityOc813LoginBinding
import com.t.mediacorp2359pocs.utils.textAsString
import timber.log.Timber

class Oc813LoginActivity : BaseMobileSsoActivity() {

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

        setUpSdkListener()
        setUpViews()
    }

    private fun setUpViews() {
        mBiding.btnLogin.setOnClickListener {
            val username = mBiding.etUsername.textAsString()
            val password = mBiding.etPassword.textAsString()

            mMobileSso.signIn(username, password)
        }
    }

    private fun setUpSdkListener() {
        mMobileSso.addAuthListener { ssoAuthStatus, message ->
            Timber.d("SSO: status: `$ssoAuthStatus` with message: `$message`")
        }
    }
}