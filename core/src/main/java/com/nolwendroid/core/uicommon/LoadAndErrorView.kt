package com.nolwendroid.core.uicommon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nolwendroid.core.di.network.ResultState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun <T> BaseView(
    state: StateFlow<ResultState<T>>,
    modifier: Modifier = Modifier,
    onRetry: (() -> Unit)? = null,
    content: @Composable (T) -> Unit
) {
    val uiState by state.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is ResultState.Idle -> {} // 🔹 Ничего не показываем
            is ResultState.Loading -> {
                CircularProgressIndicator()
            }

            is ResultState.Error -> {
                val errorMessage = (uiState as ResultState.Error).message
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Ошибка: $errorMessage", color = Color.Red, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    onRetry?.let {
                        Button(onClick = it) { Text(text = "Повторить") }
                    }
                }
            }

            is ResultState.Success -> content((uiState as ResultState.Success<T>).data)
        }
    }
}