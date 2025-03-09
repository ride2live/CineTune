package com.nolwendroid.feature_movie.domain.usecase

import android.util.Log
import com.nolwendroid.feature_movie.data.repository.MovieRepository
import com.nolwendroid.feature_movie.domain.mappers.toUi
import com.nolwendroid.feature_movie.ui.model.MovieUi
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    init {
        Log.d("Hilt-Debug", "✅ GetPopularMoviesUseCase успешно создан")
    }
    suspend operator fun invoke(page: Int): List<MovieUi> {
        return repository.getPopularMovies(page).map { it.toUi() }
    }
}