package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.IndexProto

fun IndexProto.Index.toUi(): Map<String, Any> {
    return mapOf(
        "baseImageUrl" to this.baseImageUrl,
        "baseArticleUrl" to this.baseArticleUrl,
        "navigationFeedsList" to this.navigationFeedsList.map { it.toUi() },
        "feeds" to this.feedsList.map { it.toUi() },
        "topStories" to this.topStories.toUi(),
        "latestNews" to this.latestNews.toUi(),
        "mostPopular" to this.mostPopular.toUi(),
        "misc" to this.misc.toUi(),
        "misc1" to this.misc1.toUi(),
        "inFocus" to this.inFocusList.map { it.toUi() }
    )

//    return mapOf(
//        "navigationFeedsList" to this.navigationFeedsList.map { it.toUi() }
//    )
}