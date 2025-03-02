package com.nolwendroid.feature_movie.data

import android.util.Log
import com.nolwendroid.feature_movie.api.KinopoiskApiService
import com.nolwendroid.feature_movie.domain.mappers.toDomain
import com.nolwendroid.feature_movie.domain.model.MovieKnpDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieKnpRepositoryImpl @Inject constructor(
    private val api: KinopoiskApiService
) : MovieKnpRepository {
    init {
        Log.d("Hilt-Debug", "✅ MovieKnpRepositoryImpl успешно создан")
    }
    override fun getPopularMovies(): Flow<List<MovieKnpDomain>> = flow {
        val response = api.getPopularMovies()
        println(response)
        emit(response.items.map {
            println(it)
            it.toDomain()
        })
    }
}