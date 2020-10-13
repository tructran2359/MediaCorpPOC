package com.t.mediacorp2359pocs.utils

import android.text.Spanned
import android.util.Patterns
import androidx.core.text.HtmlCompat

fun String.toHtmlSpanned(): Spanned = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)

fun List<String>.joinToStringWithLineBreak(): String = joinToString(separator = "\n")

fun String.isValidEmail(): Boolean {
    return isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}