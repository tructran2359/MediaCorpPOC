package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.SavedContentsProto

fun SavedContentsProto.SavedContents.toUi(): Map<String, Any> {
    return mapOf(
        "articlesList" to articlesList.map { it.toUi() },
        "newsClipsList" to newsClipsList.map { it.toUi() },
        "tvShowsList" to tvShowsList.map { it.toUi() },
        "episodesList" to episodesList.map { it.toUi() },
        "podcastEpisodesList" to podcastEpisodesList.map { it.toUi() }
    )
}