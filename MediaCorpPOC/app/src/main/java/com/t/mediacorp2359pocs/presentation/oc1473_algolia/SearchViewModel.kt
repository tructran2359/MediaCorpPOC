package com.t.mediacorp2359pocs.presentation.oc1473_algolia

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.algolia.instantsearch.core.connection.ConnectionHandler
import com.algolia.instantsearch.helper.android.list.SearcherSingleIndexDataSource
import com.algolia.instantsearch.helper.android.searchbox.SearchBoxConnectorPagedList
import com.algolia.instantsearch.helper.searcher.SearcherSingleIndex
import com.algolia.instantsearch.helper.stats.StatsConnector
import com.algolia.search.client.ClientSearch
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseSearch
import com.google.gson.GsonBuilder
import com.t.mediacorp2359pocs.mapper.toSearchResult
import kotlinx.serialization.json.jsonObject
import timber.log.Timber

class SearchViewModel : ViewModel() {

    companion object {
        const val APP_ID = "KKWFBQ38XF"

        const val INDEX_DEV = "cnarevamp-ezrqv5hx"
        const val INDEX_STAGING = "cnarevamp-staging-ezrqv5hx"

        const val INDEX_SUGGESTION_DEV = "cnarevamp-qs-ezrqv5hx"
        const val INDEX_SUGGESTION_STAGING = "cnarevamp-qs-staging-ezrqv5hx"

        const val API_KEY = "44f31e10e777791e5053559793a99b82"
    }

    private val mGson = GsonBuilder().registerTypeAdapter(StringList::class.java, StringListDeserializer()).create()

    private val appId: String
        get() = APP_ID

    private val apiKey: String
        get() = API_KEY

    private val indexName: String
        get() = INDEX_DEV

    private val indexSuggestion: String
        get() = INDEX_SUGGESTION_DEV

    private val mClient = ClientSearch(
        applicationID = ApplicationID(appId),
        apiKey = APIKey(apiKey)
    )

    private val mIndex = mClient.initIndex(IndexName(indexName))

    private val mSearcher = SearcherSingleIndex(index = mIndex)

    val liveSearchResults: LiveData<PagedList<SearchResult>>

    val searchBox: SearchBoxConnectorPagedList<ResponseSearch>
    private val connectionHandler = ConnectionHandler()

    val stats = StatsConnector(mSearcher)

    init {
        val dataSourceFactory: SearcherSingleIndexDataSource.Factory<SearchResult> = SearcherSingleIndexDataSource.Factory(searcher = mSearcher) { hit ->
            try {
                val jsonString = hit.json.toString()
                val highlights = hit.json["_highlightResult"]?.jsonObject

                val response = mGson.fromJson(jsonString, SearchResultResponse::class.java).apply {
                    this.highlightResult = highlights
                }

                response.toSearchResult()
            } catch (e: Exception) {
                Timber.e(e)
                SearchResult.invalidObject()
            }
        }

        liveSearchResults = LivePagedListBuilder(dataSourceFactory, 20).build()

        searchBox = SearchBoxConnectorPagedList(mSearcher, listOf(liveSearchResults))
        connectionHandler += searchBox
        connectionHandler += stats
    }

    override fun onCleared() {
        super.onCleared()
        mSearcher.cancel()
        connectionHandler.clear()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Public
    ///////////////////////////////////////////////////////////////////////////
}