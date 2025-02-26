package com.nolwendroid.feature_splash.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nolwendroid.core.navigation.AppNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CineTuneSplashScreen(navigator: AppNavigator) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            delay(2000)
            navigator.navigateToMovie()
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text="sadsdfsdf")
    }
}