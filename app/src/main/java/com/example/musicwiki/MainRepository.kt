package com.example.musicwiki

import com.example.musicwiki.data.LastFmApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val api: LastFmApi)  {

    suspend fun getTags() = api.getTags()

    suspend fun getTagDetails(tag: String) = api.getTagDetails(tag = tag)

    suspend fun getArtists(tag: String) = api.getArtists(tag = tag)

    suspend fun getAlbums(tag: String) = api.getAlbums(tag = tag)

    suspend fun getTracks(tag: String) = api.getTracks(tag = tag)
}