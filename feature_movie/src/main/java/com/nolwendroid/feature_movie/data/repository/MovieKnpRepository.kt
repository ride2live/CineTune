package com.nolwendroid.feature_movie.data.repository

import com.nolwendroid.core.di.network.ResultState
import com.nolwendroid.feature_movie.domain.model.MovieKnpDomain
import kotlinx.coroutines.flow.Flow

interface MovieKnpRepository {
    fun getPopularMovies(): Flow<ResultState<List<MovieKnpDomain>>>
}