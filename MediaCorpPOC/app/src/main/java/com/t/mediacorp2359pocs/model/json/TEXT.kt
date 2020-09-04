package com.t.mediacorp2359pocs.model.json

import com.t.mediacorp2359pocs.model.ui.UiTEXT

data class TEXT(
    val asString: String = ""
) {
    fun toUiModel(): UiTEXT {
        return UiTEXT(asString)
    }
}