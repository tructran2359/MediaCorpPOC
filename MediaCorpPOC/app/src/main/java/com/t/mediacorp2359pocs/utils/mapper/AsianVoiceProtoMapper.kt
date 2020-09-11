package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.AsianVoiceProto

fun AsianVoiceProto.AsianVoice.toUi(): Map<String, Any> {
    return mapOf(
        "citation" to citation.toUi(),
        "article" to article.toUi()
    )
}