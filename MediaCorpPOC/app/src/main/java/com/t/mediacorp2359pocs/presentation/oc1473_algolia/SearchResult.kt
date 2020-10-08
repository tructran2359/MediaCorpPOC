package com.t.mediacorp2359pocs.presentation.oc1473_algolia

import android.text.SpannedString
import android.text.style.BackgroundColorSpan
import com.algolia.instantsearch.core.highlighting.HighlightTokenizer
import com.algolia.instantsearch.core.highlighting.HighlightedString
import com.algolia.instantsearch.helper.android.highlighting.toSpannedString
import com.algolia.search.model.search.HighlightResult
import com.algolia.search.model.search.SnippetResult
import com.t.mediacorp2359pocs.MyApplication
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.utils.getColorCompat
import com.t.mediacorp2359pocs.utils.joinToStringWithLineBreak
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import timber.log.Timber

data class SearchResult(
    val categories: List<String>,

    val paragraphText: List<String>,

    val title: String,

    val topics: List<String>,

    val objectID: String,

    var highlightResult: JsonObject? = null
) {
    companion object {
        const val INVALID_ID = "INVALID_ID"
        fun invalidObject(): SearchResult = SearchResult(
            categories = emptyList(),
            paragraphText = emptyList(),
            title = "",
            topics = emptyList(),
            objectID = INVALID_ID,
            highlightResult = null
        )
    }

    val highlightedTitle: CharSequence
        get() = getHighlights("title") ?: title

    val highlightedCategories: CharSequence
        get() = getHighlights("categories") ?: categories.joinToString()

    val highlightedTopics: CharSequence
        get() = getHighlights("topics") ?: topics.joinToString()

    val highlightedParagraphText: CharSequence
        get() = getHighlights("paragraph_text") ?: paragraphText.joinToStringWithLineBreak()

    private fun HighlightedString.highlightBackground(): SpannedString {
        val color = MyApplication.sInstance.getColorCompat(
            R.color.highlight
        )
        return toSpannedString(BackgroundColorSpan(color))
    }

    private fun getHighlights(key: String): SpannedString? {
        val list = mutableListOf<HighlightResult>()

        Timber.d("Snippet: $highlightResult")
        highlightResult?.let { result ->
            val element = result[key]

            Timber.d("Element: $element")

            when (element) {
                is JsonObject -> {
                    val highlightResult = element.toHighlightResult()
                    list.add(highlightResult)
                }

                is JsonArray -> {
                    element.forEach {
                        if (it is JsonObject) {
                            val highlightResult = it.toHighlightResult()
                            list.add(highlightResult)
                        }
                    }
                }

                else -> {
                }
            }
        }

        if (list.isEmpty()) {
            return null
        }

        Timber.d(list.map { it.value }.joinToStringWithLineBreak())

        val joinedList = list.map { it.value }
            .joinToString()

        val highlightedString = HighlightTokenizer().invoke(joinedList)
        return highlightedString.highlightBackground()
    }

    private fun JsonObject.toHighlightResult(): HighlightResult {
        val json = Json.Default
        return json.decodeFromJsonElement(HighlightResult.serializer(), this)
    }

}