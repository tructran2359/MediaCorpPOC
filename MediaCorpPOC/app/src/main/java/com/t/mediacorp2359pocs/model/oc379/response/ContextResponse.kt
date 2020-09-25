package com.t.mediacorp2359pocs.model.oc379.response


import com.google.gson.annotations.SerializedName

data class ContextResponse(
    @SerializedName("content_id")
    val contentId: String = "",
    @SerializedName("cxense_id")
    val cxenseId: String = "",
    @SerializedName("meid")
    val meid: String = "",
    @SerializedName("site")
    val site: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("start_time")
    val startTime: Long = 0,
    @SerializedName("user_agent")
    val userAgent: String = "",
    @SerializedName("abtesting")
    val abtesting: AbtestingResponse = AbtestingResponse()
)