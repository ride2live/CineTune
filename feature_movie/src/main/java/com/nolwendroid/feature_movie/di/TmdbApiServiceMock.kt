package com.nolwendroid.feature_movie.di

import com.nolwendroid.feature_movie.api.TmdbApiService
import com.nolwendroid.feature_movie.data.model.MovieDTO
import com.nolwendroid.feature_movie.data.model.MovieResponse
import retrofit2.http.Query

class TmdbApiServiceMock : TmdbApiService {
    override suspend fun getPopularMovies(
        @Query(value = "language") language: String,
        @Query(value = "page") page: Int
    ): MovieResponse {
        return MovieResponse(
            results = listOf(
                MovieDTO(
                    1, "Mock Movie 1", "Overview 1", "/path1.jpg",
                    releaseDate = "",
                    voteAverage = 1.0f
                ),
                MovieDTO(
                    2, "Mock Movie 2", "Overview 2", "/path2.jpg",
                    releaseDate = "",
                    voteAverage = 1.0f
                )
            ),
            page = 1,
            totalPages = 2,
            totalResults = 10
        )
    }
    override suspend fun searchMovies(query: String, language: String, page: Int) {
        TODO("Not yet implemented")
    }
}
