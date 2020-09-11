package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.FragmentsProto

fun FragmentsProto.Fragment.toUi(): Map<String, Any> {
    return mapOf(
        "type" to type.name,
        "propertiesMap" to propertiesMap.toUi()
    )
}

fun Map<String, FragmentsProto.Fragment.Value>.toUi(): Map<String, Any> {
    val map = HashMap<String, Any>()
    forEach { entry ->
        val key = entry.key
        val value = entry.value
        val newValue: Any = when(key) {
            FragmentsProto.Fragment.Key.TEXT.name -> value.asString
            FragmentsProto.Fragment.Key.EXTENDED_TEXT.name -> value.asExtendedText.toUi()
            FragmentsProto.Fragment.Key.LIST.name -> value.asList.toUi()
            FragmentsProto.Fragment.Key.CITATION.name -> value.asCitation.toUi()
            FragmentsProto.Fragment.Key.IMAGE.name -> value.asImage.toUi()
            FragmentsProto.Fragment.Key.VIDEO.name -> value.asVideo.toUi()
            FragmentsProto.Fragment.Key.HTML.name -> value.asString
            else -> ""
        }
        map[key] = value
    }
    return map
}