package com.nolwendroid.feature_movie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nolwendroid.core.di.network.ResultState
import com.nolwendroid.core.model.MovieKnpUi
import com.nolwendroid.feature_movie.domain.usecase.GetPopularMoviesKnpUseCase
import com.nolwendroid.feature_movie.domain.usecase.GetPopularMoviesUseCase
import com.nolwendroid.feature_movie.domain.usecase.SearchMoviesKnpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSelectorViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPopularMoviesKnpUseCase: GetPopularMoviesKnpUseCase,
    private val searchMoviesKnpUseCase: SearchMoviesKnpUseCase,
) : ViewModel() {
    //    private val refreshTrigger = MutableSharedFlow<Unit>()
    private val _currentFlow = MutableStateFlow<ResultState<*>>(ResultState.Idle)
    var currentFlow: StateFlow<ResultState<*>> = _currentFlow.asStateFlow()
    private val _movies = MutableStateFlow<List<MovieKnpUi>>(mutableListOf())
    val movies: StateFlow<List<MovieKnpUi>> = _movies.asStateFlow()
    private val _searchMovies = MutableStateFlow<List<MovieKnpUi>>(mutableListOf())
    val searchMovies: StateFlow<List<MovieKnpUi>> = _searchMovies.asStateFlow()

    init {
        getMovies2()
    }


    fun addFavoriteMovie(movieKnpUi: MovieKnpUi) {
    }


    fun removeFromMovies(id: Int) {
        _movies.value = _movies.value.filter { it.id != id }.toList()
    }

    fun getMovies2() {
        collectAndUpdateState(getPopularMoviesKnpUseCase(), _movies)
    }

    fun searchMovies2(query: String) {
        collectAndUpdateState(searchMoviesKnpUseCase(query), _searchMovies)
    }

    private fun <T> collectAndUpdateState(
        flow: Flow<ResultState<List<T>>>,
        stateFlow: MutableStateFlow<List<T>>
    ) {
        viewModelScope.launch {
            flow.collect { result ->
                if (result is ResultState.Success) {
                    stateFlow.value = result.data // Обновляем данные
                }
                _currentFlow.value = result
            }
        }
    }
    fun removeFromSearch(id: Int) {
        println("removeFromSearch")
        _searchMovies.value = _searchMovies.value.filter { it.id != id }.toList()

    }
}

