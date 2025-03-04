package com.nolwendroid.feature_movie.domain

import android.util.Log
import com.nolwendroid.core.di.network.ResultState
import com.nolwendroid.feature_movie.data.MovieKnpRepository
import com.nolwendroid.core.extensions.mapResultState
import com.nolwendroid.feature_movie.domain.mappers.toUi
import com.nolwendroid.core.model.MovieKnpUi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesKnpUseCase @Inject constructor(
    private val repository: MovieKnpRepository
) {
    init {
        Log.d("Hilt-Debug", "✅ GetPopularMoviesKnpUseCase успешно создан")
    }
    operator fun invoke(): Flow<ResultState<List<MovieKnpUi>>> {
        return repository.getPopularMovies().mapResultState { list ->
            list.map {
                it.toUi()
            }
        }
    }

}