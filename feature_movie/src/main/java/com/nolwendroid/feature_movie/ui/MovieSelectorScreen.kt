package com.nolwendroid.feature_movie.ui

import com.nolwendroid.feature_movie.di.TmdbApiServiceMock
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nolwendroid.feature_movie.data.MovieRepositoryImpl
import com.nolwendroid.feature_movie.domain.GetPopularMoviesUseCase

@Composable
fun MovieSelectorScreen() {
    val viewModel: MovieSelectorViewModel = hiltViewModel()
//    val testViewModel = MovieSelectorViewModel(GetPopularMoviesUseCase(MovieRepositoryImpl(
//        TmdbApiServiceMock()
//    )))
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = "MovieScreen")


            Button(onClick = {}
            ) {
                Text("Test me")
            }
        }
    }
}