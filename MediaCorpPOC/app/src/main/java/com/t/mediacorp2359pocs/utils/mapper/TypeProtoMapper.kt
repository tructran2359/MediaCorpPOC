package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.TypesProto

fun TypesProto.Image.toUi(): Map<String, Any> {
    return mapOf(
        "id" to id,
        "ratio" to ratio,
        "caption" to caption,
        "copyright" to copyright,
        "alternativeImageUrl" to alternativeImageUrl,
        "ciaKeywordsList" to ciaKeywordsList
    )
}

fun TypesProto.Video.toUi(): Map<String, Any> {
    return mapOf(
        "id" to id,
        "preview" to preview.toUi(),
        "url" to url,
        "duration" to duration,
        "caption" to caption,
        "title" to title,
        "hlsurl" to hlsurl,
        "dashurl" to dashurl,
        "houseid" to houseid,
        "vrcontent" to vrcontent,
        "ooyalaid" to ooyalaid,
        "ooyalapcode" to ooyalapcode,
        "masterrefid" to masterrefid,
        "ciaKeywords" to ciaKeywordsList
    )
}

fun TypesProto.Author.toUi(): Map<String, Any> {
    return mapOf(
        "name" to name,
        "image" to image.toUi(),
        "twitter_account" to twitterAccount,
        "job" to job
    )
}

fun TypesProto.Citation.toUi(): Map<String, Any> {
    return mapOf(
        "text" to text,
        "author" to author.toUi()
    )
}

fun TypesProto.ExtendedText.toUi(): Map<String, Any> {
    return mapOf(
        "text" to text,
        "alignment" to alignment.name
    )
}

fun TypesProto.List.toUi(): Map<String, Any> {
    return mapOf(
        "isNumbered" to isNumbered,
        "listEntriesList" to listEntriesList
    )
}

fun TypesProto.TrendingTopics.toUi(): Map<String, Any> {
    return mapOf(
        "list" to listList
    )
}