package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.ListenIndexProto

fun ListenIndexProto.ListenIndex.toUi(): Map<String, Any> {
    return mapOf(
        "featuredEpisode" to featuredEpisode.toUi(),
        "latestEpisodesList" to latestEpisodesList.map { it.toUi() }
    )
}