package com.nolwendroid.feature_movie.ui

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nolwendroid.core.di.network.ResultState
import com.nolwendroid.feature_movie.api.TmdbApiService
import com.nolwendroid.feature_movie.domain.GetPopularMoviesKnpUseCase
import com.nolwendroid.feature_movie.domain.GetPopularMoviesUseCase
import com.nolwendroid.feature_movie.domain.mappers.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSelectorViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase, // ✅ UseCase автоматически инжектится
    private val getPopularMoviesKnpUseCase: GetPopularMoviesKnpUseCase, // ✅ UseCase автоматически инжектится
    // private val sharedPreferences: SharedPreferences // ✅ Локальное хранилище
) : ViewModel() {
    init {
        Log.d("Hilt-Debug", "✅ MovieSelectorViewModel успешно создан")
//        viewModelScope.launch {
//            try {
//                val moviesList = getPopularMoviesUseCase(1) // ✅ Получаем данные через UseCase
//                println("Чо за фигня")
//                println(moviesList)
//            } catch (e: Exception) {
//                println(e.message)
//            } finally {
//                println("got it")
//            }
//        }

        viewModelScope.launch {
            getPopularMoviesKnpUseCase().collect { result ->
                when (result) {
                    is ResultState.Success -> {
                        println("Кинопоиск ответил: ${result.data}")
                     //   cancel() // ⛔ Останавливаем корутину
                    }
                    is ResultState.Error -> {
                        println("Кинопоиск зафейлился: ${result.message}")
                     //   cancel() // ⛔ Останавливаем корутину
                    }
                    is ResultState.Loading -> {
                        println("Загрузка...")
                    }
                }
            }
        }
    }
}