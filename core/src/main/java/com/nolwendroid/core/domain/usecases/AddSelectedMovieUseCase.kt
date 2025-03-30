package com.nolwendroid.core.domain.usecases

import com.nolwendroid.core.data.repository.MovieLocalRepository
import com.nolwendroid.core.domain.model.MovieKnpDomain
import com.nolwendroid.core.model.mappers.toEntity
import javax.inject.Inject

class AddSelectedMovieUseCase @Inject constructor(
    private val repository: MovieLocalRepository
) {
    suspend operator fun invoke(movie: MovieKnpDomain) = repository.addMovie(movie.toEntity())
}