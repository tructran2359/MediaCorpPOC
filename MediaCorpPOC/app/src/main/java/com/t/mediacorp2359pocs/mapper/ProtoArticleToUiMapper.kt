package com.t.mediacorp2359pocs.mapper

import com.aperto.mediacorp.protobuf.protos.ArticleProto
import com.aperto.mediacorp.protobuf.protos.FragmentsProto
import com.aperto.mediacorp.protobuf.protos.TypesProto
import com.t.mediacorp2359pocs.model.ui.UiFragment
import com.t.mediacorp2359pocs.model.ui.UiHeaderImage
import com.t.mediacorp2359pocs.model.ui.UiModel

class ProtoHeaderImagesToUi : BaseMapper<TypesProto.Image, UiHeaderImage>() {
    override fun transform(s: TypesProto.Image): UiHeaderImage {
        return UiHeaderImage(
            id = s.id.toString(),
            ratio = s.ratio,
            caption = s.caption,
            ciaKeywords = s.ciaKeywordsList
        )
    }
}

class ProtoFragmentToUi : BaseMapper<FragmentsProto.Fragment, UiFragment>() {
    override fun transform(s: FragmentsProto.Fragment): UiFragment {
        val map = s.propertiesMap
        val text = if (map.isEmpty()) {
            ""
        } else {
            val key = map.keys.first()
            map[key]?.asString ?: ""
        }
        return UiFragment(text = text)
    }
}

fun ArticleProto.Article.toUiModel(): UiModel {
    val headerImageMapper = ProtoHeaderImagesToUi()
    val fragmentMapper = ProtoFragmentToUi()

    return UiModel(
        id = id.toString(),
        title = title,
        feedId = feedId.toString(),
        webUrl = webUrl,
        headerImages = headerImageMapper.transform(headerImagesList),
        created = created.toString(),
        lastModified = lastModified.toString(),
        keywords = keywordsList,
        copyright = copyright,
        articletype = articletype,
        categoryname = categoryname,
        docType = docType,
        adsPosition = adsPosition,
        ciaKeywords = ciaKeywordsList,
        fragments = fragmentMapper.transform(fragmentsList)
    )
}

