package com.nolwendroid.feature_movie.data.repository

import android.util.Log
import com.nolwendroid.core.di.network.RequestWrapper
import com.nolwendroid.core.di.network.ResultState
import com.nolwendroid.feature_movie.api.KinopoiskApiService
import com.nolwendroid.feature_movie.domain.mappers.toDomain
import com.nolwendroid.feature_movie.domain.model.MovieKnpDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieKnpRepositoryImpl @Inject constructor(
    private val api: KinopoiskApiService, private val requestWrapper: RequestWrapper
) : MovieKnpRepository {
    init {
        Log.d("Hilt-Debug", "✅ MovieKnpRepositoryImpl успешно создан")
    }

    override fun getPopularMovies(): Flow<ResultState<List<MovieKnpDomain>>> = flow {
        emitAll(
            requestWrapper.sendRequest(
                apiMethod = { api.getPopularMovies() },
                mapToDomain = { response ->
                    response.items.map { it.toDomain() }
                })
        )
    }
    override fun searchMovies(query: String): Flow<ResultState<List<MovieKnpDomain>>> = flow {
      emitAll(
            requestWrapper.sendRequest(
                apiMethod = { api.searchMovies(query) },
                mapToDomain = { response ->
                    println( response.movies)
                    response.movies.map { it.toDomain() }
                })
      )
    }


}