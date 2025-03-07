package com.nolwendroid.feature_movie.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nolwendroid.core.uicommon.BaseView
import com.nolwendroid.core.uicommon.draganddrop.DraggableSurface

@Composable
fun MovieSelectorScreen(
) {
    val viewModel: MovieSelectorViewModel = hiltViewModel()
    BaseView(
        state = viewModel.movies,
        onRetry = viewModel::refreshMovies,
        onRefresh = viewModel::refreshMovies
    ) { movies ->
        val moviesState = remember { mutableStateListOf(*movies.toTypedArray()) }
        DraggableSurface {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(moviesState, key = { it.id }) { movie ->
                        MovieItem(
                            movie = movie,
                            onDragEnd = {
                                moviesState.remove(movie)
                            }
                        )
                    }
                }

                Box(modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Перетащи фильм сюда",
                        color = Color.White
                    )
                }
            }
        }
    }
}
