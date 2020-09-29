package com.t.mediacorp2359pocs.presentation.oc813

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.mediacorp.mobilesso.MCMobileSSOAuthStatus
import com.t.mediacorp2359pocs.databinding.ActivityOc813LoginBinding
import com.t.mediacorp2359pocs.utils.textAsString
import com.t.mediacorp2359pocs.utils.toast
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

        mMobileSso.signOut()

        setUpViews()
    }

    private fun setUpViews() {
        mBiding.btnLogin.setOnClickListener {
            showLoading()
            val username = mBiding.etUsername.textAsString()
            val password = mBiding.etPassword.textAsString()

            mMobileSso.signIn(username, password)
        }
    }

    override fun onAuthChange(status: MCMobileSSOAuthStatus?, message: String?) {
        Timber.d("SSO: status: `$status` with message: `$message`")

        hideLoading()

        when(status) {
            MCMobileSSOAuthStatus.Authenticated -> {
//                toast("Login successfully.")
                startActivity(Oc813MyProfileActivity.getLaunchIntent(this))
                finish()
            }

            else -> {
                val errorMessage = message ?: "Something went wrong. Please try again later."
                toast(errorMessage)
            }
        }
    }

    private fun showLoading() {
        mBiding.pbLoading.isVisible = true
    }

    private fun hideLoading() {
        mBiding.pbLoading.isGone = true
    }
}