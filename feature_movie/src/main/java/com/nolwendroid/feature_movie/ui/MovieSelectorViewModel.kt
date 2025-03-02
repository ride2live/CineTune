package com.nolwendroid.feature_movie.ui

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nolwendroid.feature_movie.api.TmdbApiService
import com.nolwendroid.feature_movie.domain.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSelectorViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase // ✅ UseCase автоматически инжектится
    // private val sharedPreferences: SharedPreferences // ✅ Локальное хранилище
) : ViewModel() {
    init {
        Log.d("Hilt-Debug", "✅ MovieSelectorViewModel успешно создан")
        viewModelScope.launch {
            try {
                val moviesList = getPopularMoviesUseCase(1) // ✅ Получаем данные через UseCase
                println("Чо за фигня")
                println(moviesList)
            } catch (e: Exception) {
                println(e.message)
            } finally {
                println("got it")
            }
        }
    }
}