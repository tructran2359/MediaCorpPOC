package com.t.mediacorp2359pocs

import android.app.Application
import com.t.mediacorp2359pocs.utils.MyDebugTree
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(MyDebugTree())
    }
}