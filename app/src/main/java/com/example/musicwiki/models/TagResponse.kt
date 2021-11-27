package com.example.musicwiki.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class TagResponse(
    val toptags: TagWrapper
)

data class TagWrapper(
    val tag: List<Tag>,
    @SerializedName("@attr")
    val attr: TagAttr
)

data class TagAttr(
    val offset: Int,
    val num_res: Int,
    val total: Int
)

@Parcelize
data class Tag(
    val name: String,
    val count: Int,
    val reach: Int
): Parcelable

data class TagDetailsResponse(
    val tag: TagDetailsWrapper
)

data class TagDetailsWrapper(
    val name: String,
    val total: Int,
    val reach: Int,
    val wiki: Wiki
)

data class Wiki(
    val summary: String,
    val content: String
)
