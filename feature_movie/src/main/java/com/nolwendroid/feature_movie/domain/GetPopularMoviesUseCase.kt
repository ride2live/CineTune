package com.nolwendroid.feature_movie.domain

import com.nolwendroid.feature_movie.data.MovieRepository
import com.nolwendroid.feature_movie.domain.model.MovieDomain
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(page: Int): List<MovieDomain> {
        return repository.getPopularMovies(page)
    }
}