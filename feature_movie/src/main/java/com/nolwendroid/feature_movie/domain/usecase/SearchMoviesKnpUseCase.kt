package com.nolwendroid.feature_movie.domain.usecase

import android.util.Log
import com.nolwendroid.core.di.network.ResultState
import com.nolwendroid.core.extensions.mapResultState
import com.nolwendroid.core.model.MovieKnpUi
import com.nolwendroid.feature_movie.data.repository.MovieKnpRepository
import com.nolwendroid.feature_movie.domain.mappers.toUi
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query
import javax.inject.Inject

class SearchMoviesKnpUseCase @Inject constructor(
    private val repository: MovieKnpRepository
) {

    operator fun invoke(query: String): Flow<ResultState<List<MovieKnpUi>>> {
        return repository.searchMovies(query).mapResultState { list ->
            list.map {
                it.toUi()
            }
        }
    }

}