package com.example.musicwiki.data

import com.example.musicwiki.models.*
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmApi {

    companion object {
        const val API_KEY = "5b22ba9d115e4c814807982b3fa06d9a"
        const val BASE_URL = "https://ws.audioscrobbler.com/2.0/"
    }

    @GET(".")
    suspend fun getTagDetails(
        @Query("method") method: String = "tag.getinfo",
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json",
        @Query("tag") tag: String
    ) : TagDetailsResponse

    @GET(".")
    suspend fun getTags(
        @Query("method") method: String = "tag.getTopTags",
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json",
    ) : TagResponse

    @GET(".")
    suspend fun getAlbums(
        @Query("method") method: String = "tag.gettopalbums",
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json",
        @Query("tag") tag: String
    ) : AlbumResponse

    @GET(".")
    suspend fun getArtists(
        @Query("method") method: String = "tag.gettopartists",
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json",
        @Query("tag") tag: String
    ) : ArtistResponse

    @GET(".")
    suspend fun getTracks(
        @Query("method") method: String = "tag.gettoptracks",
        @Query("api_key") apiKey: String = API_KEY,
        @Query("format") format: String = "json",
        @Query("tag") tag: String
    ): TrackRespone

}