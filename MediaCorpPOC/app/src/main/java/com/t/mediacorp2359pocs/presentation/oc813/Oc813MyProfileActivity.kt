package com.t.mediacorp2359pocs.presentation.oc813

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.mediacorp.mobilesso.MCMobileSSOAuthStatus
import com.t.mediacorp2359pocs.databinding.ActivityOc813MyProfileBinding

class Oc813MyProfileActivity : BaseMobileSsoActivity() {

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc813MyProfileActivity::class.java)
        }
    }

    private lateinit var mBinding: ActivityOc813MyProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityOc813MyProfileBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setUpViews()

    }

    override fun onDestroy() {
        mMobileSso.signOut()
        super.onDestroy()
    }

    override fun onAuthChange(status: MCMobileSSOAuthStatus?, message: String?) {}

    private fun setUpViews() {
        title = "My Profile"

        mBinding.let { b ->
            b.tvUserName.text = mMobileSso._token._user._fullName
            b.tvEmail.text = mMobileSso._token._user._email
        }

    }
}