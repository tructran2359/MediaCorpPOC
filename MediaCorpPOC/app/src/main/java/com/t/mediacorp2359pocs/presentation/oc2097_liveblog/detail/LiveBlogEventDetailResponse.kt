package com.t.mediacorp2359pocs.presentation.oc2097_liveblog.detail

import com.google.gson.annotations.SerializedName

data class LiveBlogEventDetailResponse(
    val data: Data = Data()
) {
    data class Data(
        val event: Event = Event()
    ) {
        data class Event(
            val title: String = "",
            val cover: String = "",
            @SerializedName("keypoints")
            val keyPoints: List<KeyPoint> = emptyList()
        ) {
            data class KeyPoint(
                val nid: String,
                val title: String,
                val created: Long
            )
        }
    }
}