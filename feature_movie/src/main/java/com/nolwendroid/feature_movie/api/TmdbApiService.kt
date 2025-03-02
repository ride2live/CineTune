package com.nolwendroid.feature_movie.api


import com.nolwendroid.feature_movie.domain.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

//Api needs for only this feature, no need to hold it in core
interface TmdbApiService {
    @GET("movie/top_rated")
    suspend fun getPopularMovies(
        @Query("language") language: String = "ru-RU",
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
       // @Query("api_key") apiKey: String,- injected in interceptor
        @Query("language") language: String = "ru-RU",
        @Query("page") page: Int = 1
    ): Unit
}