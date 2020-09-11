package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.TvShowProto

fun TvShowProto.TvShow.toUi(): Map<String, Any> {
    return mapOf(
        "id" to id,
        "title" to title,
        "airTimes" to airTimes,
        "teaserText" to teaserText,
        "teaserVideo" to teaserVideo.toUi(),
        "teaserImage" to teaserImage.toUi(),
        "description" to description,
        "showUrl" to showUrl,
        "episodesList" to episodesList.map { it.toUi() },
        "webExclusiveEpisodesList" to webExclusiveEpisodesList.map { it.toUi() },
        "isSponsored" to isSponsored,
        "sponsoredHtmlFragmentsList" to sponsoredHtmlFragmentsList
    )
}