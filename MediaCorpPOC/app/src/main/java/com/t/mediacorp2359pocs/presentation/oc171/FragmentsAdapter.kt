package com.t.mediacorp2359pocs.presentation.oc171

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.model.ui.UiFragment
import com.t.mediacorp2359pocs.utils.toHtmlSpanned
import kotlinx.android.synthetic.main.item_fragment.view.*

class FragmentsAdapter(data: List<UiFragment> = listOf()) : RecyclerView.Adapter<FragmentVH>() {

    private val mItems = ArrayList<UiFragment>()
    var items: List<UiFragment>
    get() = mItems
    set(value) {
        mItems.clear()
        mItems.addAll(value)
        notifyDataSetChanged()
    }

    init {
        mItems.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentVH {
        val view = LayoutInflater.from(parent.context).inflate(FragmentVH.LAYOUT, parent, false)
        return FragmentVH(view)
    }

    override fun onBindViewHolder(holder: FragmentVH, position: Int) {
        val item = mItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }
}

class FragmentVH(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        const val LAYOUT = R.layout.item_fragment
    }

    fun bind(item: UiFragment) {
        itemView.run {
            tvContent.text = item.properties.TEXT.asString.toHtmlSpanned()
        }
    }
}