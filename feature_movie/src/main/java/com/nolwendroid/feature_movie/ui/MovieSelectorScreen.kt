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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nolwendroid.core.model.MovieKnpUi
import com.nolwendroid.core.uicommon.BaseView
import com.nolwendroid.core.uicommon.draganddrop.DragTargetInfo
import com.nolwendroid.core.uicommon.draganddrop.DraggableSurface
import com.nolwendroid.core.uicommon.draganddrop.LocalDragTargetInfo

@Composable
fun MovieSelectorScreen() {
    val viewModel: MovieSelectorViewModel = hiltViewModel()
    var dragInfo by remember { mutableStateOf(DragTargetInfo()) }

    BaseView(
        state = viewModel.movies,
        onRetry = viewModel::refreshMovies,
        onRefresh = viewModel::refreshMovies
    ) { movies ->
        val moviesState = remember { mutableStateListOf(*movies.toTypedArray()) }
        DraggableSurface(content = {
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
                MovieDropArea(viewModel)
            }
        })
    }
}

@Composable
fun MovieDropArea(viewModel: MovieSelectorViewModel) {
    val dragInfo =
        LocalDragTargetInfo.current // Получаем доступ к состоянию через LocalDragTargetInfo
    var isCurrentDropTarget by remember { mutableStateOf(false) }
    var isDraggingLocal: Boolean
    var dropAreaBounds by remember { mutableStateOf(androidx.compose.ui.geometry.Rect.Zero) }
    LaunchedEffect(dragInfo.isDragging, dragInfo.dragOffset) {
        isDraggingLocal = dragInfo.isDragging


        println("MovieDropArea обновлен isDraggingLocal: $isDraggingLocal")
        println("MovieDropArea обновлен isCurrentDropTarget: $isCurrentDropTarget")
        if (isCurrentDropTarget) {

            if (!isDraggingLocal) {
                println(dragInfo.dataToDrop as MovieKnpUi)
            }
        }
        isCurrentDropTarget = dropAreaBounds.contains(dragInfo.dragPosition + dragInfo.dragOffset)
    }



    Box(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .onGloballyPositioned {
                dropAreaBounds = it.boundsInWindow() // Сохраняем границы в переменную
            }
            .background(if (isCurrentDropTarget) Color.Red else Color.Gray)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Перетащи фильм сюда",
            color = Color.White
        )
    }
}

