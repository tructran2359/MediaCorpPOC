package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.FeedProto


fun FeedProto.Feed.toUi(): Map<String, Any> {
    return mapOf(
        "meta" to meta.toUi(),
        "isSpecialReport" to isSpecialReport,
        "topics" to topicsList.map { it.toUi() }
    )
}