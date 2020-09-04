package com.t.mediacorp2359pocs.model.json

import com.t.mediacorp2359pocs.model.ui.UiHeaderImage

data class HeaderImage(
    val id: String = "",
    val ratio: String = "",
    val caption: String = "",
    val ciaKeywords: List<String> = listOf()
) {
    fun toUiModel(): UiHeaderImage {
        return UiHeaderImage(
            id = id,
            ratio = ratio,
            caption = caption,
            ciaKeywords = ciaKeywords
        )
    }
}