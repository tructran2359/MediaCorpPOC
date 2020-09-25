package com.t.mediacorp2359pocs.model.oc379.response


import com.google.gson.annotations.SerializedName

data class LayoutParamsResponse(
    @SerializedName("row")
    val row: Int = 0,
    @SerializedName("column")
    val column: Int = 0,
    @SerializedName("slotGroupMapping")
    val slotGroupMapping: SlotGroupMappingResponse = SlotGroupMappingResponse()
)