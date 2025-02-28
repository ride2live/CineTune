package com.nolwendroid.cinetune.navigation

import androidx.compose.runtime.Composable
import com.nolwendroid.core.navigation.NavigationProvider
import com.nolwendroid.feature_movie.ui.MovieSelectorScreen
import com.nolwendroid.feature_splash.ui.CineTuneSplashScreen
import javax.inject.Inject


class NavigationProviderImpl @Inject constructor(): NavigationProvider {
    override fun splashScreen(): @Composable () -> Unit = { CineTuneSplashScreen() }
    override fun movieSelectorScreen(): @Composable () -> Unit = { MovieSelectorScreen() }
}