package com.t.mediacorp2359pocs.utils

import android.webkit.WebView

fun WebView.loadHtml(htmlString: String) {
    loadDataWithBaseURL("", htmlString, "text/html", "UTF-8", "")
}