package com.nolwendroid.feature_movie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.nolwendroid.core.model.MovieKnpUi
import com.nolwendroid.feature_movie.domain.usecase.GetPopularMoviesKnpUseCase
import com.nolwendroid.feature_movie.domain.usecase.GetPopularMoviesUseCase
import com.nolwendroid.feature_movie.domain.usecase.SearchMoviesKnpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class MovieSelectorViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getPopularMoviesKnpUseCase: GetPopularMoviesKnpUseCase,
    private val searchMoviesKnpUseCase: SearchMoviesKnpUseCase,
) : ViewModel() {
    private val favoriteMovies = MutableStateFlow<Set<MovieKnpUi>>(emptySet())  // Избранные
    private val dislikedMovies = MutableStateFlow<Set<MovieKnpUi>>(emptySet())  // Нелюбимые
    private val searchQuery = MutableStateFlow("")
    private val refreshTrigger = MutableStateFlow(Unit)

    private val popularMoviesPagingFlow = refreshTrigger
        .flatMapLatest { getPopularMoviesKnpUseCase() }
        .cachedIn(viewModelScope) // Кешируем, чтобы не пересоздавался `Flow`

    val movies: Flow<PagingData<MovieKnpUi>> = combine(
        popularMoviesPagingFlow, // `PagingData` не пересоздаётся
        favoriteMovies,
        dislikedMovies
    ) { pagingData, favorites, disliked ->
        pagingData.filter { movie -> movie !in favorites && movie !in disliked }
    }

    val searchMovies: Flow<PagingData<MovieKnpUi>> = searchQuery
        .flatMapLatest { query ->
            if (query.isEmpty()) flowOf(PagingData.empty())
            else searchMoviesKnpUseCase(query)
        }
        .cachedIn(viewModelScope)

    fun refreshMovies() {
        refreshTrigger.value = Unit
    }

    fun addFavoriteMovie(movieKnpUi: MovieKnpUi) {
        favoriteMovies.value += movieKnpUi
    }

    fun addDislikedMovie(movieKnpUi: MovieKnpUi) {
        dislikedMovies.value += movieKnpUi
    }

    fun searchMovies(query: String) {
        searchQuery.value = query
    }
}

