package com.nolwendroid.feature_movie.data.repository

import androidx.paging.PagingSource
import com.nolwendroid.core.domain.model.MovieKnpDomain

interface MovieKnpRepository {
    fun getPopularMovies(): PagingSource<Int, MovieKnpDomain>
    fun searchMovies(query: String): PagingSource<Int, MovieKnpDomain>
}