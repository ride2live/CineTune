package com.nolwendroid.core.domain.usecases

import com.nolwendroid.core.data.repository.MovieLocalRepository
import com.nolwendroid.core.data.room.movies.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedMoviesUseCase @Inject constructor(
    private val repository: MovieLocalRepository
) {
    operator fun invoke(): Flow<List<MovieEntity>> = repository.getSelectedMovies()
}