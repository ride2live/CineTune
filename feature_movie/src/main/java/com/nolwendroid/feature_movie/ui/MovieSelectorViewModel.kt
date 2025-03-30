package com.nolwendroid.feature_movie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.nolwendroid.core.domain.usecases.AddSelectedMovieUseCase
import com.nolwendroid.core.domain.usecases.AddSelectedMoviesUseCase
import com.nolwendroid.core.domain.usecases.GetSelectedMoviesUseCase
import com.nolwendroid.core.domain.usecases.RemoveSelectedMoviesUseCase
import com.nolwendroid.core.model.MovieKnpUi
import com.nolwendroid.core.model.SelectedType
import com.nolwendroid.feature_movie.domain.mappers.toDomain
import com.nolwendroid.feature_movie.domain.usecase.GetPopularMoviesKnpUseCase
import com.nolwendroid.feature_movie.domain.usecase.SearchMoviesKnpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSelectorViewModel @Inject constructor(
    private val getPopularMoviesKnpUseCase: GetPopularMoviesKnpUseCase,
    private val searchMoviesKnpUseCase: SearchMoviesKnpUseCase,
    private val removeSelectedMoviesUseCase: RemoveSelectedMoviesUseCase,
    private val addSelectedMoviesUseCase: AddSelectedMoviesUseCase,
    private val addSelectedMovieUseCase: AddSelectedMovieUseCase,
    private val getSelectedMoviesUseCase: GetSelectedMoviesUseCase,
) : ViewModel() {
    private val favoriteMovies = MutableStateFlow<Set<MovieKnpUi>>(emptySet())  // Избранные
    private val dislikedMovies = MutableStateFlow<Set<MovieKnpUi>>(emptySet())  // Нелюбимые
    private val searchQuery = MutableStateFlow("")
    private val refreshTriggerPopular = MutableStateFlow(Unit)
    private val refreshTriggerSearch = MutableStateFlow(Unit)
    private val popularMoviesPagingFlow = refreshTriggerPopular
        .flatMapLatest { getPopularMoviesKnpUseCase() }
        .cachedIn(viewModelScope)

    // Поток поиска фильмов (PagingData)
    private val searchMoviesPagingFlow = searchQuery
        .flatMapLatest { query ->
            if (query.isEmpty()) flowOf(PagingData.empty()) else searchMoviesKnpUseCase(query)
        }
        .cachedIn(viewModelScope)
    val searchMovies: Flow<PagingData<MovieKnpUi>> = combine(
        searchMoviesPagingFlow,
        favoriteMovies.asStateFlow(),
        dislikedMovies.asStateFlow()
    ) { pagingData, favorites, disliked ->
        pagingData.filter { movie -> movie !in favorites && movie !in disliked }
    }
    val movies: Flow<PagingData<MovieKnpUi>> = combine(
        popularMoviesPagingFlow,
        favoriteMovies,
        dislikedMovies
    ) { pagingData, favorites, disliked ->
        pagingData.filter { movie -> movie !in favorites && movie !in disliked }
    }

    fun refreshMovies() {
        refreshTriggerPopular.value = Unit
    }

    fun refreshSearch(query: String) {
        searchQuery.value = query
    }

    fun rateMovie(movieKnpUi: MovieKnpUi, dragType: DragType) {
        val newSelectedType = when (dragType) {
            DragType.LIKE -> {
                SelectedType.FAVORITE
            }

            DragType.DISLIKE -> {
                SelectedType.DISLIKED
            }
        }
        favoriteMovies.value += movieKnpUi
        movieKnpUi.selectedType = newSelectedType
        viewModelScope.launch {
            addSelectedMovieUseCase(movieKnpUi.toDomain())
            getSelectedMoviesUseCase().collect{
                println(it)
            }
        }

    }
}

