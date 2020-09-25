package com.t.mediacorp2359pocs.model.oc379.response


import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("site")
    val site: String = "",
    @SerializedName("layoutConfig")
    val layoutConfig: LayoutConfigResponse = LayoutConfigResponse(),
    @SerializedName("slotCount")
    val slotCount: Int = 0,
    @SerializedName("tags")
    val tags: List<String> = listOf(),
    @SerializedName("items")
    val items: List<ItemResponse> = listOf()
)