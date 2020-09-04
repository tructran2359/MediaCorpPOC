package com.t.mediacorp2359pocs.model.json

import com.t.mediacorp2359pocs.model.ui.UiProperties

data class Properties(
    val TEXT: TEXT = TEXT()
) {
    fun toUiModel(): UiProperties {
        return UiProperties(TEXT = TEXT.toUiModel())
    }
}