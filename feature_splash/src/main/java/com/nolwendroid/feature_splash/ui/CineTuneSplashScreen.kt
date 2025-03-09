package com.nolwendroid.feature_splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.nolwendroid.feature_splash.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CineTuneSplashScreen(onNavigateToMovie: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            delay(500)
            onNavigateToMovie()
        }
    }
    Surface (modifier = Modifier.background(Color.Red)){
        Box(
            modifier = Modifier.fillMaxSize().background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = "App Icon"
            )
        }
    }
}