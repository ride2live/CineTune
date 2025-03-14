package com.nolwendroid.feature_movie.data.repository

import androidx.paging.PagingSource
import com.nolwendroid.core.di.network.ResultState
import com.nolwendroid.feature_movie.domain.model.MovieKnpDomain
import kotlinx.coroutines.flow.Flow

interface MovieKnpRepository {
    fun getPopularMovies(): PagingSource<Int, MovieKnpDomain>
    fun searchMovies(query: String): PagingSource<Int, MovieKnpDomain>
}