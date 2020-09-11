package com.t.mediacorp2359pocs.model.json

import com.t.mediacorp2359pocs.model.ui.UiModel

data class JsonResponse(
    val id: String = "",
    val title: String = "",
    val feedId: String = "",
    val webUrl: String = "",
    val headerImages: List<HeaderImage> = listOf(),
    val created: String = "",
    val lastModified: String = "",
    val keywords: List<String> = listOf(),
    val copyright: String = "",
    val articletype: String = "",
    val categoryname: String = "",
    val docType: String = "",
    val adsPosition: String = "",
    val ciaKeywords: List<String> = listOf(),
    val fragments: List<Fragment> = listOf()
) {
    fun toUiModel(): UiModel {
        return UiModel(
            id = id,
            title = title,
            feedId = feedId,
            webUrl = webUrl,
            headerImages = headerImages.map { it.toUiModel() },
            created = created,
            lastModified = lastModified,
            keywords = keywords,
            copyright = copyright,
            articletype = articletype,
            categoryname = categoryname,
            docType = docType,
            adsPosition = adsPosition,
            ciaKeywords = ciaKeywords,
            fragments = fragments.map { it.toUiModel() }
        )
    }
}

class LargeJsonResponse : HashMap<String, Any>()

const val SPACE = '*'

fun Map<*,*>.flatten(level: Int = 0): List<Data> {
    val list = arrayListOf<Data>()
    forEach { entry ->
        list.add(Key(level, entry.key.toString()))

        when(entry.value) {
            is Map<*, *> -> {
                list.addAll((entry.value as  Map<*,*>).flatten(level + 1))
            }

            is List<*> -> {
                list.addAll((entry.value as List<*>).flatten(level + 1))
            }

            else -> {
                list.add(Value(level, entry.value.toString()))
            }
        }
    }
    return list
}

fun Any.flatten(level: Int = 0): List<Data> {
    val list = arrayListOf<Data>()

    when(this) {
        is Map<*, *> -> {
            list.addAll(this.flatten(level))
        }

        is List<*> -> {
            forEach {
                it?.let { data ->
                    list.addAll(data.flatten(level))
                }

            }
        }

        else -> {
            list.add(Value(level, this.toString()))
        }
    }

    return list
}

sealed class Data(val level: Int, val data: String)

class Key(level: Int, key: String): Data(level, key){
    override fun toString(): String {
        return data
    }
}

class Value(level: Int, value: String): Data(level, value){
    override fun toString(): String {
        return data
    }
}
