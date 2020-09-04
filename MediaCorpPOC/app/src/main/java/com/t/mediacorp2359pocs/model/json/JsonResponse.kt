package com.t.mediacorp2359pocs.model.json

import com.t.mediacorp2359pocs.model.ui.UiModel

data class JsonResponse(
    val id: String = "",
    val title: String = "",
    val feedId: String = "",
    val webUrl: String = "",
    val headerImages: List<HeaderImage> = listOf(),
    val created: String = "",
    val lastModified: String = "",
    val keywords: List<String> = listOf(),
    val copyright: String = "",
    val articletype: String = "",
    val categoryname: String = "",
    val docType: String = "",
    val adsPosition: String = "",
    val ciaKeywords: List<String> = listOf(),
    val fragments: List<Fragment> = listOf()
) {
    fun toUiModel(): UiModel {
        return UiModel(
            id = id,
            title = title,
            feedId = feedId,
            webUrl = webUrl,
            headerImages = headerImages.map { it.toUiModel() },
            created = created,
            lastModified = lastModified,
            keywords = keywords,
            copyright = copyright,
            articletype = articletype,
            categoryname = categoryname,
            docType = docType,
            adsPosition = adsPosition,
            ciaKeywords = ciaKeywords,
            fragments = fragments.map { it.toUiModel() }
        )
    }
}