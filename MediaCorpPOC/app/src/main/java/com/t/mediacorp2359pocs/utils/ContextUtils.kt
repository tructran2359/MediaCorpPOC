package com.t.mediacorp2359pocs.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import timber.log.Timber

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.openExternalBrowser(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)

    try {
        startActivity(intent)
    } catch (e: Exception) {
        Timber.e(e)
        toast("Can not open url : \"$url\"")
    }
}