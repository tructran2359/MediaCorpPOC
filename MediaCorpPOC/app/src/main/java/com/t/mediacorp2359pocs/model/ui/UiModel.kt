package com.t.mediacorp2359pocs.model.ui

data class UiModel(
    val id: String,
    val title: String,
    val feedId: String,
    val webUrl: String,
    val headerImages: List<UiHeaderImage>,
    val created: String,
    val lastModified: String,
    val keywords: List<String>,
    val copyright: String,
    val articletype: String,
    val categoryname: String,
    val docType: String,
    val adsPosition: String,
    val ciaKeywords: List<String>,
    val fragments: List<UiFragment>,
) {
    companion object {
        fun dummy(): UiModel {
            return UiModel(
                id = "",
                title = "",
                feedId = "",
                webUrl = "",
                headerImages = listOf(),
                created = "",
                lastModified = "",
                keywords = listOf(),
                copyright = "",
                articletype = "",
                categoryname = "",
                docType = "",
                adsPosition = "",
                ciaKeywords = listOf(),
                fragments = listOf()
            )
        }
    }
}