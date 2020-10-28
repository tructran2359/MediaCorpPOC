package com.t.mediacorp2359pocs.utils

import java.text.SimpleDateFormat
import java.util.*
import timber.log.Timber

const val SERVER_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssX"
const val LOCAL_DATE_TIME_FORMAT = "yyyy MMM dd - HH:mm:ss"
const val KEY_POINT_TIME_FORMAT = "h:mm a"

fun String.fromServerToLocalDateTime(): String {
    Timber.d("DateFormat: Ori: $this")
    var result = this
    try {
        val serverFormat = SERVER_DATE_TIME_FORMAT.sdf
        val localFormat = LOCAL_DATE_TIME_FORMAT.sdf

        val serverDate = serverFormat.parse(this)

        serverDate?.let { date ->
            result = localFormat.format(date)
        }
    } catch (e: Exception) {
        Timber.e(e)
    }
    Timber.d("DateFormat: New: $result")
    return result
}

val String.sdf: SimpleDateFormat
    get() = SimpleDateFormat(this, Locale.getDefault())

fun Long.toKeyPointTimeFormat(): String {
    val date = Date(this)
    return KEY_POINT_TIME_FORMAT.sdf.format(date)
}