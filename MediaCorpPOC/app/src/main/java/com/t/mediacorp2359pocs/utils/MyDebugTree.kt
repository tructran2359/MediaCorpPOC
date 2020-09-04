package com.t.mediacorp2359pocs.utils

import timber.log.Timber

class MyDebugTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val newTag = "POCs_$tag"
        super.log(priority, newTag, message, t)
    }
}