package com.nolwendroid.feature_movie.data

import com.nolwendroid.feature_movie.domain.model.MovieDomain
import com.nolwendroid.feature_movie.domain.model.MovieKnpDomain
import kotlinx.coroutines.flow.Flow

interface MovieKnpRepository {
    fun getPopularMovies(): Flow<List<MovieKnpDomain>>
}