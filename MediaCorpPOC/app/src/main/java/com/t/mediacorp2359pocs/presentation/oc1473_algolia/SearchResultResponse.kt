package com.t.mediacorp2359pocs.presentation.oc1473_algolia


import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import kotlinx.serialization.json.JsonObject

data class SearchResultResponse(

    @SerializedName("brief")
    val brief: String = "",

    @SerializedName("categories")
    val categories: StringList = StringList(),

    @SerializedName("paragraph_text")
    val paragraphText: StringList = StringList(),

    @SerializedName("title")
    val title: String = "",

    @SerializedName("topics")
    val topics: StringList = StringList(),

    @SerializedName("objectID")
    val objectID: String = ""
) {
    var highlightResult: JsonObject? = null
}

///////////////////////////////////////////////////////////////////////////
// Custom Deserializer
///////////////////////////////////////////////////////////////////////////

data class StringList(
    var values: List<String> = emptyList()
)

class StringListDeserializer : JsonDeserializer<StringList> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): StringList {

        val stringList = StringList()

        if (json?.isJsonArray == true) {
            stringList.values = context?.deserialize(json, object : TypeToken<List<String>>(){}.type) ?: emptyList()
        } else {
            stringList.values = if (json != null) {
                listOf(json.asString)
            } else {
                emptyList()
            }
        }

        return stringList
    }
}