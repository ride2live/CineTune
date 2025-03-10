package com.nolwendroid.feature_movie.data.repository

import com.nolwendroid.feature_movie.domain.model.MovieDomain

interface MovieRepository {
    suspend fun getPopularMovies(page: Int):  List<MovieDomain>
}