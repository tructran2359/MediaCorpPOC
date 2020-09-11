package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.TopicProto

fun TopicProto.Topic.toUi(): Map<String, Any> {
    return mapOf(
        "meta" to meta.toUi(),
        "topicType" to topicType.name,
        "articlesList" to articlesList.map { it.toUi() },
        "asianVoicesList" to asianVoicesList.map { it.toUi() },
        "rhythmBreakerBannersList" to rhythmBreakerBannersList.map { it.toUi() },
        "headlineNewsList" to headlineNewsList.map { it.toUi() }
    )
}