package com.t.mediacorp2359pocs.presentation.oc171

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.t.mediacorp2359pocs.R
import com.t.mediacorp2359pocs.model.json.Data
import com.t.mediacorp2359pocs.model.json.Key
import com.t.mediacorp2359pocs.model.json.SPACE
import com.t.mediacorp2359pocs.utils.toHtmlSpanned
import kotlinx.android.synthetic.main.item_from_map.view.tvContent

class ResponseAdapter : RecyclerView.Adapter<JsonResponseVH>() {

    private val mItems = arrayListOf<Data>()
    var items: List<Data>
    get() = mItems
    set(value) {
        mItems.clear()
        mItems.addAll(value)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JsonResponseVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_from_map, parent, false)
        return JsonResponseVH(view)
    }

    override fun onBindViewHolder(holder: JsonResponseVH, position: Int) {
        val item = mItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }
}

class JsonResponseVH(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(data: Data) {
        itemView.run {
            var content = data.data
            val level = content.indexOfFirst { it != SPACE }
            val space = context.resources.getDimensionPixelSize(R.dimen.item_offset)
            if (level != 0) {
                content = content.substring(startIndex = level)
            }
            tvContent.text = content.toHtmlSpanned()
            val layoutParams = tvContent.layoutParams as LinearLayout.LayoutParams
            layoutParams.marginStart = level * space
            tvContent.layoutParams = layoutParams
            val textColor = if (data is Key) {
                Color.BLUE
            } else {
                Color.BLACK
            }
            val backgroundColor = if (data is Key) {
                Color.GREEN
            } else {
                Color.parseColor("#2200FF00")
            }

            tvContent.setTextColor(textColor)
            tvContent.setBackgroundColor(backgroundColor)
        }
    }
}