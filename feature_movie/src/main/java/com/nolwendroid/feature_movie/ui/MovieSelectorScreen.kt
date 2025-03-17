package com.nolwendroid.feature_movie.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.nolwendroid.core.R
import com.nolwendroid.core.model.MovieKnpUi
import com.nolwendroid.core.uicommon.BaseView
import com.nolwendroid.core.uicommon.draganddrop.DraggableSurface
import com.nolwendroid.core.uicommon.draganddrop.LocalDragTargetInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MovieSelectorScreen() {
    val viewModel: MovieSelectorViewModel = hiltViewModel()
    val movies = viewModel.movies.collectAsLazyPagingItems()
    val moviesSearchState = viewModel.searchMovies.collectAsLazyPagingItems()
    var isSearching by remember { mutableStateOf(false) }
    val resultMovies by remember {
        derivedStateOf {
            if (moviesSearchState.itemCount > 0 && isSearching) {
                moviesSearchState
            } else {
                isSearching = false
                movies
            }
        }
    }
    BaseView(
        pagingItems = resultMovies,
        onRetry = viewModel::refreshMovies,
        onRefresh = viewModel::refreshMovies, content = {
            DraggableSurface(content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Spacer(modifier = Modifier.height(12.dp))
                    SearchBarMovies(viewModel, {
                        isSearching = true
                    }) {
                        isSearching = false
                    }
                    LazyRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        items(resultMovies.itemCount / 2, key = { index ->
                            resultMovies[index * 2]?.id ?: index
                        }) { index ->
                            val positionOfFirst = index * 2
                            val positionOfSecond = positionOfFirst + 1
                            val firstMovie = resultMovies[positionOfFirst]
                            val secondMovie =
                                if (positionOfSecond < resultMovies.itemCount) resultMovies[positionOfSecond] else null

                            Column {
                                firstMovie?.let {
                                    key(it.id) { MovieItem(movie = it) }
                                }
                                secondMovie?.let {
                                    key(it.id) { MovieItem(movie = it) }
                                }
                            }
                        }
                    }
                    Row {
                        Column(modifier = Modifier.weight(1f)) {
                            MovieDropArea(viewModel, DragType.DISLIKE)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            MovieDropArea(viewModel, DragType.LIKE)
                        }
                    }
                }
            })
        }
    )
}

@Composable
fun MovieDropArea(viewModel: MovieSelectorViewModel, dragType: DragType) {
    val dragInfo = LocalDragTargetInfo.current
    var isCurrentDropTarget by remember { mutableStateOf(false) }
    var isDraggingLocal: Boolean
    var dropAreaBounds by remember { mutableStateOf(androidx.compose.ui.geometry.Rect.Zero) }
    LaunchedEffect(dragInfo.isDragging, dragInfo.dragOffset) {
        isDraggingLocal = dragInfo.isDragging
        if (isCurrentDropTarget && !isDraggingLocal) {
            val movieKnpUi = dragInfo.dataToDrop as MovieKnpUi
            viewModel.rateMovie(movieKnpUi, dragType)
        }
        isCurrentDropTarget = dropAreaBounds.contains(dragInfo.dragPosition + dragInfo.dragOffset)
    }


    Box(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth().padding(8.dp)
            .onGloballyPositioned {
                dropAreaBounds = it.boundsInWindow() // Сохраняем границы в переменную
            }
            .drawBehind {
                val pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f)
                drawRoundRect(
                    color = Color.Gray,
                    style = Stroke(width = 2.dp.toPx(), pathEffect = pathEffect),
                    cornerRadius = CornerRadius(24.dp.toPx())
                )
            }
            .background(if (isCurrentDropTarget) Color.LightGray else Color.Unspecified, shape = RoundedCornerShape(24.dp)), contentAlignment = Alignment.Center
    )
    {
        when(dragType){
            DragType.LIKE -> {
                Image(
                    painter = painterResource(id = R.drawable.ic_like),
                    contentDescription = "Like", modifier = Modifier.size(48.dp),
                    colorFilter = ColorFilter.tint(Color.Gray)
                )
            }
            DragType.DISLIKE -> {
                Image(
                    painter = painterResource(id = R.drawable.ic_dislike),
                    contentDescription = "Dislike", modifier = Modifier.size(48.dp),
                    colorFilter = ColorFilter.tint(Color.Gray)
                )
            }
        }
    }
}

@Composable
fun SearchBarMovies(
    viewModel: MovieSelectorViewModel,
    onSearchStarted: () -> Unit,
    onClose: () -> Unit
) {
    val searchQueryFlow = remember { MutableStateFlow("") }
    val searchQuery by searchQueryFlow.collectAsState()
    LaunchedEffect(searchQueryFlow) {
        searchQueryFlow
            .collectLatest { query ->
                delay(700)
                if (query.length > 2) {
                    onSearchStarted()
                    viewModel.refreshSearch(query)
                }
            }
    }
    Row() {
        SearchBarMoviesV2(query = searchQuery, onQueryChange = {
            searchQueryFlow.value = it
        }, onClose = onClose)
//        CloseButton {
//            onClose()
//        }
    }
}

@Composable
fun SearchBarMoviesV2(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search")
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = {
                    onQueryChange("")
                    onClose()
                }) {
                    Icon(Icons.Default.Close, contentDescription = "Clear search")
                }
            }
        },
        placeholder = { Text("Search") },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF5F5F5),
            unfocusedContainerColor = Color(0xFFF5F5F5),
            disabledContainerColor = Color(0xFFF5F5F5),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(8.dp, RoundedCornerShape(20.dp))
    )
}

enum class DragType {
    LIKE,
    DISLIKE
}




