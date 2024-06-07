package com.muzafferatmaca.androidtvcompose.domain.entity

import java.io.Serializable

/**
 * Created by Muzaffer Atmaca on 7.06.2024 at 23:55
 */
data class Movie(
    var id: Long = 0,
    var title: String = "",
    var description: String = "",
    var backgroundImageUrl: String = "",
    var cardImageUrl: String = "",
    var videoUrl: String = "",
    var studio: String = ""
) : Serializable {

    override fun toString(): String {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", backgroundImageUrl='" + backgroundImageUrl + '\'' +
                ", cardImageUrl='" + cardImageUrl + '\'' +
                '}'
    }

    companion object {
        internal const val serialVersionUID = 727566175075960653L
    }
}