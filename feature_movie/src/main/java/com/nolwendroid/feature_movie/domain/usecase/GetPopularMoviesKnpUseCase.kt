package com.nolwendroid.feature_movie.domain.usecase

import android.util.Log
import com.nolwendroid.core.di.network.ResultState
import com.nolwendroid.core.extensions.mapResultState
import com.nolwendroid.core.model.MovieKnpUi
import com.nolwendroid.feature_movie.data.repository.MovieKnpRepository
import com.nolwendroid.feature_movie.domain.mappers.toUi
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