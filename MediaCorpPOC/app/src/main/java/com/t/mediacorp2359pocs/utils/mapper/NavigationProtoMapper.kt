package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.NavigationProto


fun NavigationProto.Navigation.toUi(): Map<String, Any> {
    return mapOf(
        "meta" to this.meta.toUi(),
        "isSpecialReport" to isSpecialReport,
        "topics" to topicsList.map { it.toUi() }
    )
}