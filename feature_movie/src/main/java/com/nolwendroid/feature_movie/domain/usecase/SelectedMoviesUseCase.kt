package com.nolwendroid.feature_movie.domain.usecase

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.nolwendroid.core.di.network.ResultState
import com.nolwendroid.core.extensions.mapResultState
import com.nolwendroid.core.model.MovieKnpUi
import com.nolwendroid.feature_movie.data.repository.MovieKnpRepository
import com.nolwendroid.feature_movie.domain.mappers.toUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SelectedMoviesUseCase @Inject constructor(
    private val repository: MovieKnpRepository
) {
    init {
        Log.d("Hilt-Debug", "✅ GetPopularMoviesKnpUseCase успешно создан")
    }

    operator fun invoke(): Flow<PagingData<MovieKnpUi>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { repository.getPopularMovies() }
        ).flow.map { pagingData ->
            pagingData.map { it.toUi() }
        }
    }
}