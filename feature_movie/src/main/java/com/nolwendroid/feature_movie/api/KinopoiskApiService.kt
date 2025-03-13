package com.nolwendroid.feature_movie.api


import com.nolwendroid.feature_movie.data.model.MovieKnpResponseDto
import com.nolwendroid.feature_movie.data.model.MovieKnpSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//Api needs for only this feature, no need to hold it in core
interface KinopoiskApiService {
    @GET("v2.2/films/collections")
    suspend fun getPopularMovies(
        @Query("type") type: String = "TOP_250_MOVIES",
        @Query("page") page: Int = 1
    ): MovieKnpResponseDto

    @GET("v2.1/films/search-by-keyword")
    suspend fun searchMovies(
        @Query("keyword") keyword: String,
        @Query("page") page: Int = 1
    ): Response<MovieKnpSearchResponse>
}