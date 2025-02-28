package com.nolwendroid.cinetune.navigation

import androidx.compose.runtime.Composable
import com.nolwendroid.feature_movie.ui.MovieSelectorScreen
import com.nolwendroid.feature_splash.ui.CineTuneSplashScreen
import javax.inject.Inject


class MyNavigationProviderImpl @Inject constructor(): MyNavigationProvider {
    override fun splashScreen(): @Composable () -> Unit = { CineTuneSplashScreen() }
    override fun movieSelectorScreen(): @Composable () -> Unit = { MovieSelectorScreen() }
}