package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.PodcastProto

fun PodcastProto.Podcast.toUi(): Map<String, Any> {
    return mapOf(
        "id" to id,
        "title" to title,
        "airTimes" to airTimes,
        "teaserText" to teaserText,
        "teaserImage" to teaserImage.toUi(),
        "description" to description,
        "showUrl" to showUrl,
        "podcastFeedUrl" to podcastFeedUrl,
        "episodesList" to episodesList.map { it.toUi() }
    )
}