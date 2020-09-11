package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.EpisodeProto

fun EpisodeProto.Episode.toUi(): Map<String, Any> {
    return mapOf(
        "id" to id,
        "title" to title,
        "description" to description,
        "number" to number,
        "webUrl" to webUrl,
        "created" to created,
        "isWebExclusive" to isWebExclusive,
        "video" to video.toUi(),
        "subShowsList" to subShowsList,
        "tvShowId" to tvShowId,
        "tvShowTitle" to tvShowTitle
    )
}