package com.t.mediacorp2359pocs.model.oc379.response


import com.google.gson.annotations.SerializedName

data class ItemResponse(

    @SerializedName("algorithm")
    val algorithm: String = "",

    @SerializedName("id")
    val id: String = "",

    @SerializedName("ranking_algorithm")
    val rankingAlgorithm: String = "",

    @SerializedName("ranking_score")
    val rankingScore: Int = 0,

    @SerializedName("content_id")
    val contentId: String = "",

    @SerializedName("publish_date")
    val publishDate: String = "",

    @SerializedName("section")
    val section: String = "",

    @SerializedName("section_label")
    val sectionLabel: String = "",

    @SerializedName("site")
    val site: String = "",

    @SerializedName("title")
    val title: String = "",

    @SerializedName("abstract")
    val `abstract`: String = "",

    @SerializedName("slot_group")
    val slotGroup: String = "",

    @SerializedName("thumbnail")
    val thumbnail: String = "",

    @SerializedName("image")
    val image: String = "",

    @SerializedName("thumbnail_type")
    val thumbnailType: String = "",

    @SerializedName("url")
    val url: String = "",

    @SerializedName("url_encrypted")
    val urlEncrypted: String = "",

    @SerializedName("slot_position")
    val slotPosition: String = "",

    @SerializedName("click_through_url")
    val clickThroughUrl: String = "",

    @SerializedName("click_tracker")
    val clickTracker: String = "",

    @SerializedName("info")
    val info: String = ""
)