package com.t.mediacorp2359pocs.mapper

import com.t.mediacorp2359pocs.model.oc379.response.ItemResponse
import com.t.mediacorp2359pocs.model.oc379.ui.Widget


fun ItemResponse.toUi() = Widget(
    id = id,
    thumbnail = thumbnail,
    publishDate = publishDate,
    section = sectionLabel,
    title = title,
    clickTracker = clickTracker
)

fun List<ItemResponse>.toUi() = map { it.toUi() }