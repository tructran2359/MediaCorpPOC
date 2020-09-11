package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.BreakingNewsProto

fun BreakingNewsProto.BreakingNews.toUi(): Map<String, Any> {
    return mapOf(
        "id" to id,
        "headline" to headline,
        "articleId" to articleId
    )
}