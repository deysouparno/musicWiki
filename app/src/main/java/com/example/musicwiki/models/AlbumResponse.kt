package com.example.musicwiki.models

import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    val albums: AlbumWrapper
)

data class AlbumWrapper(
    val album: List<Album>,
    @SerializedName("@attr")
    val attr: WrapperAttr
)

data class WrapperAttr(
    val tag: String,
    val page: String,
    val perPage: String,
    val totalPages: String,
    val total: String,

) {
    fun getPage() = page.toInt()
    fun perPage() = perPage.toInt()
    fun totalPages() = totalPages.toInt()
    fun total() = total.toInt()
}

data class Album(
    val name: String,
    val mbid: String,
    val url: String,
    val artist: Artist,
    val image: List<ImageResponse>,
    @SerializedName("@attr")
    val attr: Attr
)

data class Attr(val rank: String) {
    fun getRank() = rank.toInt()
}

data class ImageResponse(
    @SerializedName("#text")
    val text: String,
    val size: String
)

data class Artist(
    val name: String,
    val mbid: String,
    val url: String
)
