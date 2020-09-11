package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.PodcastEpisodeProto

fun PodcastEpisodeProto.PodcastEpisode.toUi(): Map<String, Any> {
    return mapOf(
        "id" to id,
        "title" to title,
        "description" to description,
        "number" to number,
        "webUrl" to webUrl,
        "created" to created,
        "duration" to duration,
        "showTimes" to showTimes,
        "podcastId" to podcastId,
        "podcastTitle" to podcastTitle,
        "podcastImage" to podcastImage.toUi(),
        "pageUrl" to pageUrl
    )
}