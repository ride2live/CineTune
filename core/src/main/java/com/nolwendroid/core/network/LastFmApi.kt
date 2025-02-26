package com.nolwendroid.core.network

import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmApi {
    @GET("?method=track.search&format=json")
    suspend fun searchTrack(
        @Query("track") track: String,
        @Query("api_key") apiKey: String
    )
}