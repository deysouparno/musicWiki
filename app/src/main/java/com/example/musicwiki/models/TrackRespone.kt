package com.example.musicwiki.models

import com.google.gson.annotations.SerializedName

data class TrackRespone(
    val tracks: TrackWrapper
)
data class TrackWrapper(
    val track: List<Track>,
    @SerializedName("@attr")
    val attr: WrapperAttr
)

data class Track(
    val name: String,
    val duration: String,
    val mbid: String,
    val url: String,
    val artist: Artist,
    val image: List<ImageResponse>,
    @SerializedName("@attr")
    val attr: Attr,
    val streamable: Streamable
)
data class Streamable(
    val fulltrack: String,
    @SerializedName("#text")
    val text: String
)
