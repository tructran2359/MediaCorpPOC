package com.t.mediacorp2359pocs.utils

import android.graphics.Paint
import android.widget.TextView
import androidx.core.view.isGone

fun TextView.underline() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun TextView.textAsString(): String {
    return text?.toString() ?: ""
}

var TextView.textOrHide: CharSequence?
    get() = textAsString()
    set(value) {
        text = value
        isGone = value.isNullOrBlank()
    }