package com.nolwendroid.feature_movie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.nolwendroid.core.di.network.ResultState
import com.nolwendroid.core.model.MovieKnpUi
import com.nolwendroid.feature_movie.domain.usecase.GetPopularMoviesKnpUseCase
import com.nolwendroid.feature_movie.domain.usecase.GetPopularMoviesUseCase
import com.nolwendroid.feature_movie.domain.usecase.SearchMoviesKnpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSelectorViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPopularMoviesKnpUseCase: GetPopularMoviesKnpUseCase,
    private val searchMoviesKnpUseCase: SearchMoviesKnpUseCase,
) : ViewModel() {


    private val favoriteMovies = MutableStateFlow<Set<MovieKnpUi>>(emptySet())  // Избранные
    private val dislikedMovies = MutableStateFlow<Set<MovieKnpUi>>(emptySet())  // Нелюбимые

    private val _currentFlow = MutableStateFlow<ResultState<*>>(ResultState.Idle)
    var currentFlow: StateFlow<ResultState<*>> = _currentFlow.asStateFlow()
    private val _movies = MutableStateFlow<List<MovieKnpUi>>(mutableListOf())
   // val movies: StateFlow<List<MovieKnpUi>> = _movies.asStateFlow()
    private val _searchMovies = MutableStateFlow<List<MovieKnpUi>>(mutableListOf())
    val searchMovies: StateFlow<List<MovieKnpUi>> = _searchMovies.asStateFlow()
    private val refreshTrigger = MutableStateFlow(Unit)

    private val pagingFlow = refreshTrigger
        .flatMapLatest { getPopularMoviesKnpUseCase() }
        .cachedIn(viewModelScope) // Кешируем, чтобы не пересоздавался `Flow`

    val movies: Flow<PagingData<MovieKnpUi>> = combine(
        pagingFlow, // `PagingData` не пересоздаётся
        favoriteMovies,
        dislikedMovies
    ) { pagingData, favorites, disliked ->
        pagingData.filter { movie -> movie !in favorites && movie !in disliked }
    }


    fun refreshMovies() {
        refreshTrigger.value = Unit
    }
    fun addFavoriteMovie(movieKnpUi: MovieKnpUi) {
        favoriteMovies.value += movieKnpUi
    }
    fun addDislikedMovie(movieKnpUi: MovieKnpUi) {
        dislikedMovies.value += movieKnpUi

    }


    fun removeFromMovies(id: Int) {
     //   _movies.value = _movies.value.filter { it.id != id }.toList()
    }

//    fun getMovies2() {
//        collectAndUpdateState(getPopularMoviesKnpUseCase(), _movies)
//    }

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

