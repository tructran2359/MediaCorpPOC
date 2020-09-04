package com.t.mediacorp2359pocs.presentation.oc171

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.model.ui.UiHeaderImage
import kotlinx.android.synthetic.main.item_header_image.view.*

class HeaderImagesAdapter(data: List<UiHeaderImage> = listOf()) : RecyclerView.Adapter<HeaderImageVH>() {

    private val mItems = ArrayList<UiHeaderImage>()
    var items: List<UiHeaderImage>
    get() = mItems
    set(value) {
        mItems.clear()
        mItems.addAll(value)
        notifyDataSetChanged()
    }

    init {
        mItems.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderImageVH {
        val view = LayoutInflater.from(parent.context).inflate(HeaderImageVH.LAYOUT, parent, false)
        return HeaderImageVH(view)
    }

    override fun onBindViewHolder(holder: HeaderImageVH, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class HeaderImageVH(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        const val LAYOUT = R.layout.item_header_image
    }

    fun bind(item: UiHeaderImage) {
        itemView.run {
            tvId.text = item.id
            tvRatio.text = item.ratio
            tvCaption.text = item.caption
            tvCiaKeywords.text = item.ciaKeywords.joinToString(separator = "\n")
        }
    }
}