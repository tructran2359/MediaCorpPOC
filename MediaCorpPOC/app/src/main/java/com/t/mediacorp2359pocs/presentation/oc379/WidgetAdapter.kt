package com.t.mediacorp2359pocs.presentation.oc379

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.model.oc379.ui.Widget
import com.t.mediacorp2359pocs.utils.inflateItemView
import com.t.mediacorp2359pocs.utils.load
import kotlinx.android.synthetic.main.item_widget.view.ivThumbnail
import kotlinx.android.synthetic.main.item_widget.view.tvPublishDate
import kotlinx.android.synthetic.main.item_widget.view.tvSection
import kotlinx.android.synthetic.main.item_widget.view.tvTitle

class WidgetAdapter : ListAdapter<Widget, WidgetVH>(WidgetDiffCallback()) {

    var itemClick: ((Widget) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WidgetVH {
        val view = parent.inflateItemView(WidgetVH.LAYOUT)
        return WidgetVH(view)
    }

    override fun onBindViewHolder(holder: WidgetVH, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
        holder.itemView.setOnClickListener {
            itemClick?.invoke(item)
        }
    }
}

class WidgetVH(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        const val LAYOUT = R.layout.item_widget
    }

    fun bind(widget: Widget) {
        itemView.run {
            ivThumbnail.load(widget.thumbnail)
            tvTitle.text = widget.title
            tvPublishDate.text = widget.publishDate
            tvSection.text = widget.section
        }
    }
}

class WidgetDiffCallback : DiffUtil.ItemCallback<Widget>() {

    override fun areItemsTheSame(oldItem: Widget, newItem: Widget): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Widget, newItem: Widget): Boolean {
        return oldItem == newItem
    }
}