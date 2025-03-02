package com.nolwendroid.feature_movie.domain

import android.util.Log
import com.nolwendroid.feature_movie.data.MovieKnpRepository
import com.nolwendroid.feature_movie.data.MovieRepository
import com.nolwendroid.feature_movie.domain.mappers.toUi
import com.nolwendroid.feature_movie.domain.model.MovieDomain
import com.nolwendroid.feature_movie.domain.model.MovieKnpDomain
import com.nolwendroid.feature_movie.ui.model.MovieUi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesKnpUseCase @Inject constructor(
    private val repository: MovieKnpRepository
) {
    init {
        Log.d("Hilt-Debug", "✅ GetPopularMoviesUseCase успешно создан")
    }
    operator fun invoke(): Flow<List<MovieKnpDomain>> = repository.getPopularMovies()

}