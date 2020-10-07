package com.t.mediacorp2359pocs.presentation.oc1473_algolia

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.databinding.ItemSearchResultBinding
import com.t.mediacorp2359pocs.utils.inflateItemView

class SearchResultAdapter : PagedListAdapter<SearchResult, SearchResultVH>(SearchResultDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultVH {
        val view = parent.inflateItemView(SearchResultVH.LAYOUT)
        return SearchResultVH(view)
    }

    override fun onBindViewHolder(holder: SearchResultVH, position: Int) {
        val result = getItem(position)
        result?.let {
            holder.bind(it)
        }
    }
}

///////////////////////////////////////////////////////////////////////////
// ViewHolder
///////////////////////////////////////////////////////////////////////////

class SearchResultVH(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        const val LAYOUT = R.layout.item_search_result
    }

    private val mBinding = ItemSearchResultBinding.bind(view)

    fun bind(result: SearchResult) {
        mBinding.tvName.text = result.title
    }
}

///////////////////////////////////////////////////////////////////////////
// Diff Callback
///////////////////////////////////////////////////////////////////////////

class SearchResultDiffUtils : DiffUtil.ItemCallback<SearchResult>() {
    override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
        return oldItem.objectID == newItem.objectID
    }

    override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
        return oldItem == newItem
    }
}