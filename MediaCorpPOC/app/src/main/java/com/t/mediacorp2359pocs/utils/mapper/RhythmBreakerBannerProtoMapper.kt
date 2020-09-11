package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.RhythmBreakerBannerProto

fun RhythmBreakerBannerProto.RhythmBreakerBanner.toUi(): Map<String, Any> {
    return mapOf(
        "title" to title,
        "description" to description,
        "extendedDescription" to extendedDescription,
        "imageId" to imageId,
        "hasCtaButton" to hasCtaButton,
        "buttonLabel" to buttonLabel,
        "ctaUrl" to ctaUrl,
        "position" to position
    )
}