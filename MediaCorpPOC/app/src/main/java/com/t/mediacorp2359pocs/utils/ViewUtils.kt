package com.t.mediacorp2359pocs.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflateItemView(@LayoutRes layoutResId: Int): View {
    return LayoutInflater.from(context).inflate(layoutResId, this, false)
}