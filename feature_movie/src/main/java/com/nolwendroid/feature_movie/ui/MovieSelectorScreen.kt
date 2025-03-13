package com.nolwendroid.feature_movie.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.nolwendroid.core.model.MovieKnpUi
import com.nolwendroid.core.uicommon.BaseView
import com.nolwendroid.core.uicommon.draganddrop.DraggableSurface
import com.nolwendroid.core.uicommon.draganddrop.LocalDragTargetInfo
import com.nolwendroid.core.uicommon.icons.CloseButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MovieSelectorScreen() {
    val viewModel: MovieSelectorViewModel = hiltViewModel()
    val movies = viewModel.movies.collectAsLazyPagingItems()
    val moviesSearchState by viewModel.searchMovies.collectAsState()
    var isSearching by remember { mutableStateOf(false) }
//    val movies2 = if (moviesSearchState.isNotEmpty() && isSearching) {
//        moviesSearchState
//    } else {
//        isSearching = false
//        movies
//    }
//    val scrollState = rememberScrollState()
//    val firstRow = moviesAny.filterIndexed { index, _ -> index % 2 == 0 }
//    val secondRow = moviesAny - firstRow.toSet()
    BaseView(
        state = viewModel.currentFlow,
        onRetry = viewModel::refreshMovies,
        onRefresh = viewModel::refreshMovies, content = {
            DraggableSurface(content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    SearchBarMovies(viewModel, {
                        isSearching = true
                    }) {
                        isSearching = false
                    }
                    LazyRow(modifier = Modifier.fillMaxSize().weight(1f)) {
                        items(movies.itemCount) { index ->
                            movies[index]?.let { MovieItem(movie = it) }
                        }
                    }
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .horizontalScroll(scrollState),
//                        verticalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        Row(modifier = Modifier.fillMaxWidth()) {
//                            firstRow.forEach { movie ->
//                                key(movie.id) {
//                                    MovieItem(movie = movie)
//                                }
//                            }
//                        }
//                        Row(modifier = Modifier.fillMaxWidth()) {
//                            secondRow.forEach { movie ->
//                                key(movie.id) {
//                                    MovieItem(movie = movie)
//                                }
//                            }
//                        }
//                    }
                    MovieDropArea(viewModel, isSearching)
                }
            })
        }
    )
}

@Composable
fun MovieDropArea(viewModel: MovieSelectorViewModel, isSearching: Boolean) {
    val dragInfo = LocalDragTargetInfo.current
    var isCurrentDropTarget by remember { mutableStateOf(false) }
    var isDraggingLocal: Boolean
    var dropAreaBounds by remember { mutableStateOf(androidx.compose.ui.geometry.Rect.Zero) }
    LaunchedEffect(dragInfo.isDragging, dragInfo.dragOffset) {
        isDraggingLocal = dragInfo.isDragging
        if (isCurrentDropTarget && !isDraggingLocal) {
            val movieKnpUi = dragInfo.dataToDrop as MovieKnpUi
            viewModel.addFavoriteMovie(movieKnpUi)
            if (isSearching) {
                viewModel.removeFromSearch(movieKnpUi.id)
            } else {
                viewModel.removeFromMovies(movieKnpUi.id)
            }
        }
        isCurrentDropTarget = dropAreaBounds.contains(dragInfo.dragPosition + dragInfo.dragOffset)
    }

    Box(modifier = Modifier
        .height(120.dp)
        .fillMaxWidth()
        .onGloballyPositioned {
            dropAreaBounds = it.boundsInWindow() // Сохраняем границы в переменную
        }
        .background(if (isCurrentDropTarget) Color.Red else Color.Gray)) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Перетащи фильм сюда",
            color = Color.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarMovies(
    viewModel: MovieSelectorViewModel,
    onSearchStarted: () -> Unit,
    onClose: () -> Unit
) {
    val expanded by rememberSaveable { mutableStateOf(false) }
    val searchQueryFlow = remember { MutableStateFlow("") }
    val searchQuery by searchQueryFlow.collectAsState()


    LaunchedEffect(searchQueryFlow) {
        searchQueryFlow
            // .debounce(500) // Задержка перед выполнением
            .collectLatest { query ->
                delay(1000)
                if (query.length > 2) {
                    onSearchStarted()
                    viewModel.searchMovies2(query)
                }
            }
    }
    Row() {
        SearchBar(modifier = Modifier.weight(1f), expanded = expanded, inputField = {
            TextField(value = searchQuery, onValueChange = {
                searchQueryFlow.value = it
            })
        }, onExpandedChange = {}) {
        }
        CloseButton {
            onClose()
        }
    }
}




