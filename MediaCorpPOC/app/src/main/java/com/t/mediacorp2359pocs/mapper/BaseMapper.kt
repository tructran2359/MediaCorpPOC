package com.t.mediacorp2359pocs.mapper

abstract class BaseMapper <SOURCE, DESTINATION> {

    abstract fun transform(s: SOURCE): DESTINATION

    fun transform(listSource: List<SOURCE>): List<DESTINATION> {
        return listSource.map { transform(it) }
    }
}