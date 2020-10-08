package com.t.mediacorp2359pocs.utils

import timber.log.Timber

class MyDebugTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String? {
        return "POC_${super.createStackElementTag(element)}(${element.fileName}:${element.lineNumber})"
    }
}