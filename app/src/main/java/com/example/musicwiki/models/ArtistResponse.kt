package com.example.musicwiki.models

import com.google.gson.annotations.SerializedName

data class ArtistResponse(
    val topartists: ArtistWrapper
)
data class ArtistWrapper(
    val artist: List<ArtistDetails>,
    @SerializedName("@attr")
    val attr: ArtistAttr
)
data class ArtistAttr(
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
data class ArtistDetails(
    val name: String,
    val mbid: String,
    val url: String,
    val streamable: String,
    val image: List<ImageResponse>,
    @SerializedName("@attr")
    val attr: Attr
)
