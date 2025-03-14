package com.nolwendroid.core.uicommon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nolwendroid.core.di.network.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseView(
    modifier: Modifier = Modifier,
    onRetry: (() -> Unit)? = null,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit,
    state: StateFlow<ResultState<*>> = MutableStateFlow<ResultState<*>>(ResultState.Idle)
) {
    var isRefreshing by remember { mutableStateOf(false) }
    val uiState by state.collectAsState()
    PullToRefreshBox(
        onRefresh = {
            isRefreshing = true
            onRefresh()
        }, modifier = modifier, isRefreshing = isRefreshing
    ) {
        Box(
            modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            content()
            when (uiState) {
                is ResultState.Idle -> {}
                is ResultState.Loading -> {
                    isRefreshing = false
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f)), // Полупрозрачный фон
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color.White) // Белый лоадер
                    }
                }

                is ResultState.Error -> {
                    isRefreshing = false
                    val errorMessage = (uiState as ResultState.Error).message
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f)), // Полупрозрачный фон
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Ошибка: $errorMessage", color = Color.Red, fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            onRetry?.let {
                                Button(onClick = it) { Text(text = "Повторить") }
                            }
                        }
                    }
                }

                is ResultState.Success -> {
                    isRefreshing = false
                }
            }
        }
    }
}