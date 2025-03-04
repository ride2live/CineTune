package com.nolwendroid.feature_movie.api


import com.nolwendroid.feature_movie.data.model.MovieKnpResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//Api needs for only this feature, no need to hold it in core
interface KinopoiskApiService {
    @GET("films/collections")
    suspend fun getPopularMovies(
        @Query("type") type: String = "TOP_250_MOVIES",
        @Query("page") page: Int = 1
    ): Response<MovieKnpResponseDto>

}