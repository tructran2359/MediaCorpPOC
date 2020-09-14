package com.t.mediacorp2359pocs.utils

import com.google.gson.Gson

fun Any.toJson(): String = Gson().toJson(this)