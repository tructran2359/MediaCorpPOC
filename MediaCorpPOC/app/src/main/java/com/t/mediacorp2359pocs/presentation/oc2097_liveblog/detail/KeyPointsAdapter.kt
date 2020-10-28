package com.t.mediacorp2359pocs.presentation.oc2097_liveblog.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.t.mediacorp2359pocs.databinding.ItemKeyPointBinding
import com.t.mediacorp2359pocs.presentation.oc2097_liveblog.detail.LiveBlogEventDetailResponse.Data.Event.KeyPoint
import com.t.mediacorp2359pocs.utils.toKeyPointTimeFormat

class KeyPointsAdapter : ListAdapter<KeyPoint, KeyPointVH>(KeyPointDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyPointVH {
        val binding = ItemKeyPointBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KeyPointVH(binding)
    }

    override fun onBindViewHolder(holder: KeyPointVH, position: Int) {
        getItem(position)?.let {
            holder.bind(it, position == 0)
        }
    }
}

class KeyPointVH(private val mBinding: ItemKeyPointBinding) : RecyclerView.ViewHolder(mBinding.root) {

    fun bind(keyPoint: KeyPoint, isFirstItem: Boolean) {
        mBinding.tvTime.text = keyPoint.created.toKeyPointTimeFormat()
        mBinding.tvTitle.text = keyPoint.title
        mBinding.vTopLine.isInvisible = isFirstItem
    }
}

class KeyPointDiffUtils : DiffUtil.ItemCallback<KeyPoint>() {

    override fun areItemsTheSame(oldItem: KeyPoint, newItem: KeyPoint): Boolean {
        return oldItem.nid == newItem.nid
    }

    override fun areContentsTheSame(oldItem: KeyPoint, newItem: KeyPoint): Boolean {
        return oldItem == newItem
    }
}

