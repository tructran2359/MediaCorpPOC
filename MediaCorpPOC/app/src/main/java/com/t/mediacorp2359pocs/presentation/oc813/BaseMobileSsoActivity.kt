package com.t.mediacorp2359pocs.presentation.oc813

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mediacorp.mobilesso.MCMobileSSO
import com.mediacorp.mobilesso.MCMobileSSOAuthStatus
import com.mediacorp.mobilesso.MCMobileSSOListener
import com.t.mediacorp2359pocs.MyApplication

abstract class BaseMobileSsoActivity : AppCompatActivity() {

    protected lateinit var mMobileSso: MCMobileSSO
    private val mAuthListener = MCMobileSSOListener { status, message ->
        this@BaseMobileSsoActivity.onAuthChange(status, message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMobileSso = MyApplication.sInstance.mcMobileSso
    }

    override fun onResume() {
        super.onResume()
        mMobileSso.addAuthListener(mAuthListener)
    }

    override fun onPause() {
        super.onPause()
        mMobileSso.removeAuthListener(mAuthListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        mMobileSso._delegate.onActivityResult(requestCode, resultCode, data)
    }

    protected abstract fun onAuthChange(status: MCMobileSSOAuthStatus?, message: String?)
}