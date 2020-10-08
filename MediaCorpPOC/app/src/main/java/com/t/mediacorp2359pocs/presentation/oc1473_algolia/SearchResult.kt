package com.t.mediacorp2359pocs.presentation.oc1473_algolia

data class SearchResult(
    val categories: List<String>,

    val paragraphText: List<String>,

    val title: String,

    val topics: List<String>,

    val objectID: String
) {
    companion object {
        const val INVALID_ID = "INVALID_ID"
        fun invalidObject(): SearchResult = SearchResult(
            categories = emptyList(),
            paragraphText = emptyList(),
            title = "",
            topics = emptyList(),
            objectID = INVALID_ID
        )
    }
}