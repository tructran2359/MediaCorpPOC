package com.t.mediacorp2359pocs.model.oc379.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Widget(
    val id: String,
    val thumbnail: String,
    val publishDate: String,
    val section: String,
    val title: String,
    val clickTracker: String
) : Parcelable