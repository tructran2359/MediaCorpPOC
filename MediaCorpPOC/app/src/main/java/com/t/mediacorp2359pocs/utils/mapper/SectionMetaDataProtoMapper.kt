package com.t.mediacorp2359pocs.utils.mapper

import com.aperto.mediacorp.protobuf.protos.SectionMetaDataProto


fun SectionMetaDataProto.SectionMetaData.toUi(): Map<String, Any> {
    return mapOf(
        "id" to this.id,
        "title" to this.title,
        "lastModified" to lastModified,
        "color" to color,
        "headerImage" to headerImage.toUi(),
        "sectionUrl" to sectionUrl
    )
}