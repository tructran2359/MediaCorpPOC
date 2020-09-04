package com.t.mediacorp2359pocs.utils

import androidx.core.text.HtmlCompat

fun String.toHtmlSpanned() = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)

fun List<String>.joinToStringWithLineBreak(): String = joinToString(separator = "\n")