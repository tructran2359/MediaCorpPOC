package com.t.mediacorp2359pocs

import android.app.Application
import com.mediacorp.mobilesso.MCMobileSSO
import com.t.mediacorp2359pocs.utils.MyDebugTree
import timber.log.Timber

class MyApplication : Application() {
    
    companion object {
        const val MC_CLIENT_ID = "05bf8e42-5bb6-468c-9c89-143b20ba47e7"
        const val MC_CLIENT_SECRET = "66DFD35B-B889-4FC8-8793-8B664CA0102F"
        const val MC_TOKEN_EXPIRY_IN_MINS = 60L

        lateinit var sInstance: MyApplication
    }

    private var _mcMobileSSO: MCMobileSSO? = null
    val mcMobileSso: MCMobileSSO
        get() {
            val immutableObject = _mcMobileSSO
            return if (immutableObject == null) {
                val nonNullObj = MCMobileSSO.getInstance()
                nonNullObj.initialise(this, MC_CLIENT_ID, MC_CLIENT_SECRET, MC_TOKEN_EXPIRY_IN_MINS)
                _mcMobileSSO = nonNullObj

                nonNullObj
            } else {
                immutableObject
            }
        }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        Timber.plant(MyDebugTree())
    }
}