package com.nolwendroid.feature_movie.data

import android.util.Log
import com.nolwendroid.feature_movie.api.TmdbApiService
import com.nolwendroid.feature_movie.domain.mappers.toDomain
import com.nolwendroid.feature_movie.domain.model.MovieDomain
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: TmdbApiService
) : MovieRepository {
    init {
        Log.d("Hilt-Debug", "✅ MovieRepositoryImpl успешно создан")
    }
    override suspend fun getPopularMovies(page: Int): List<MovieDomain> {
        return api.getPopularMovies(page = page).results.map {

            it.toDomain() }
    }
}