package com.nolwendroid.feature_movie.data

import com.nolwendroid.core.di.network.ResultState
import com.nolwendroid.feature_movie.data.model.MovieKnpDto
import com.nolwendroid.feature_movie.data.model.MovieKnpResponseDto
import com.nolwendroid.feature_movie.domain.model.MovieDomain
import com.nolwendroid.feature_movie.domain.model.MovieKnpDomain
import kotlinx.coroutines.flow.Flow

interface MovieKnpRepository {
    fun getPopularMovies(): Flow<ResultState<List<MovieKnpDomain>>>
}