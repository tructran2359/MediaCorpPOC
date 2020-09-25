package com.t.mediacorp2359pocs.model.oc379.response


import com.google.gson.annotations.SerializedName

data class WidgetsResponse(
    @SerializedName("data")
    val data: DataResponse = DataResponse(),
    @SerializedName("context")
    val context: ContextResponse = ContextResponse()
)