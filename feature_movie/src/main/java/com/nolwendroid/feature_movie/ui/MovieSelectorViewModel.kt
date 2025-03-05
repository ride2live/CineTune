package com.nolwendroid.feature_movie.ui

import android.util.Log
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
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase, // ✅ UseCase автоматически инжектится
    private val getPopularMoviesKnpUseCase: GetPopularMoviesKnpUseCase, // ✅ UseCase автоматически инжектится
) : ViewModel() {
//    val movies: StateFlow<ResultState<List<MovieKnpUi>>> = getPopularMoviesKnpUseCase()
//        .stateIn(viewModelScope, SharingStarted.Lazily, ResultState.Idle)
    private val refreshTrigger = MutableSharedFlow<Unit>()

    val movies: StateFlow<ResultState<List<MovieKnpUi>>> = refreshTrigger
        .onStart { emit(Unit) } // При запуске сразу загружаем данные
        .flatMapLatest { getPopularMoviesKnpUseCase() }
        .stateIn(viewModelScope, SharingStarted.Lazily, ResultState.Idle)

    fun refreshMovies() {
        viewModelScope.launch {
            refreshTrigger.emit(Unit)
        }
    }
}

