package com.t.mediacorp2359pocs.mapper

import com.t.mediacorp2359pocs.presentation.oc1473_algolia.SearchResult
import com.t.mediacorp2359pocs.presentation.oc1473_algolia.SearchResultResponse

fun SearchResultResponse.toSearchResult(): SearchResult {
    return SearchResult(
        brief = brief,
        categories = categories.values,
        paragraphText = paragraphText.values,
        title = title,
        topics = topics.values,
        objectID = objectID,
        highlightResult = highlightResult
    )
}