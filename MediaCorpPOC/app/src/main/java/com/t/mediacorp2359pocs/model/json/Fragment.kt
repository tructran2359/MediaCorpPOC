package com.t.mediacorp2359pocs.model.json

import com.t.mediacorp2359pocs.model.ui.UiFragment

data class Fragment(
    val properties: Properties = Properties()
) {
    fun toUiModel(): UiFragment {
        return UiFragment(text = properties.TEXT.asString)
    }
}