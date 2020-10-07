package com.t.mediacorp2359pocs.presentation.oc1473_algolia

data class SearchResult(
    val categories: String,

    val paragraphText: List<String>,

    val title: String,

    val topics: List<String>,

    val objectID: String
) {
    companion object {
        const val INVALID_ID = "INVALID_ID"
        fun invalidObject(): SearchResult = SearchResult(
            categories = "",
            paragraphText = listOf(),
            title = "",
            topics = listOf(),
            objectID = INVALID_ID
        )
    }
}