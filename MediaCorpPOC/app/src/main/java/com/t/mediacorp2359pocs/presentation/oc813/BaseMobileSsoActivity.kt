package com.t.mediacorp2359pocs.presentation.oc813

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mediacorp.mobilesso.MCMobileSSO
import com.t.mediacorp2359pocs.MyApplication

abstract class BaseMobileSsoActivity : AppCompatActivity() {

    protected lateinit var mMobileSso: MCMobileSSO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMobileSso = MyApplication.sInstance.mcMobileSso
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        mMobileSso._delegate.onActivityResult(requestCode, resultCode, data)
    }
}