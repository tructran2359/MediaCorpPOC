package com.t.mediacorp2359pocs.model.oc379.response


import com.google.gson.annotations.SerializedName

data class LayoutConfigResponse(
    @SerializedName("layoutParams")
    val layoutParams: LayoutParamsResponse = LayoutParamsResponse(),
    @SerializedName("layout")
    val layout: String = "",
    @SerializedName("itemStyle")
    val itemStyle: String = "",
    @SerializedName("css")
    val css: String = "",
    @SerializedName("title")
    val title: String = ""
)