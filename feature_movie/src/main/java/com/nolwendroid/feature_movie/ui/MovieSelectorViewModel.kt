package com.nolwendroid.feature_movie.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nolwendroid.core.di.network.ResultState
import com.nolwendroid.feature_movie.domain.GetPopularMoviesKnpUseCase
import com.nolwendroid.feature_movie.domain.GetPopularMoviesUseCase
import com.nolwendroid.core.model.MovieKnpUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.stateIn


@HiltViewModel
class MovieSelectorViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase, // ✅ UseCase автоматически инжектится
    private val getPopularMoviesKnpUseCase: GetPopularMoviesKnpUseCase, // ✅ UseCase автоматически инжектится
    // private val sharedPreferences: SharedPreferences // ✅ Локальное хранилище
) : ViewModel() {


        val movies: StateFlow<ResultState<List<MovieKnpUi>>> = getPopularMoviesKnpUseCase()
            .stateIn(viewModelScope, SharingStarted.Lazily, ResultState.Idle)


}