package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.ArticleProto

fun ArticleProto.Article.toUi(): Map<String, Any> {
    return mapOf(
        "id" to id,
        "title" to title,
        "authors" to authorsList.map { it.toUi() },
        "feedId" to feedId,
        "categoryname" to categoryname,
        "webUrl" to webUrl,
        "headerImages" to headerImagesList.map { it.toUi() },
        "headerVideos" to headerVideosList.map { it.toUi() },
        "numberOfShares" to numberOfShares,
        "created" to created,
        "lastModified" to lastModified,
        "keywords" to keywordsList,
        "isLiveDeveloping" to isLiveDeveloping,
        "isSponsored" to isSponsored,
        "sponsorName" to sponsorName,
        "sponsorImage" to sponsorImage.toUi(),
        "isExclusive" to isExclusive,
        "isAnalysis" to isAnalysis,
        "isNewsClip" to isNewsClip,
        "copyright" to copyright,
        "articletype" to articletype,
        "brief" to brief,
        "fragmentsList" to fragmentsList.map { it.toUi() },
        "docType" to docType,
        "adsPosition" to adsPosition,
        "ciaKeywordsList" to ciaKeywordsList
    )
}