package com.nolwendroid.feature_movie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nolwendroid.core.di.network.ResultState
import com.nolwendroid.feature_movie.domain.GetPopularMoviesKnpUseCase
import com.nolwendroid.feature_movie.domain.GetPopularMoviesUseCase
import com.nolwendroid.core.model.MovieKnpUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class MovieSelectorViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPopularMoviesKnpUseCase: GetPopularMoviesKnpUseCase,
) : ViewModel() {

    private val refreshTrigger = MutableSharedFlow<Unit>()

    val movies: StateFlow<ResultState<List<MovieKnpUi>>> = refreshTrigger
        .onStart { emit(Unit) }
        .flatMapLatest { getPopularMoviesKnpUseCase() }
        .stateIn(viewModelScope, SharingStarted.Lazily, ResultState.Idle)

    fun refreshMovies() {
        viewModelScope.launch {
            refreshTrigger.emit(Unit)
        }
    }
    fun addFavoriteMovie(movieKnpUi: MovieKnpUi) {

    }
}

