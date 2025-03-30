package com.nolwendroid.core.domain.usecases

import com.nolwendroid.core.data.repository.MovieLocalRepository
import com.nolwendroid.core.data.room.movies.MovieEntity
import javax.inject.Inject

class AddSelectedMoviesUseCase @Inject constructor(
    private val repository: MovieLocalRepository
) {
    suspend operator fun invoke(moviesList : List<MovieEntity>) = repository.addMovies(moviesList)
}