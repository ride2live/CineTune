package com.nolwendroid.cinetune.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.Navigator
import com.nolwendroid.core.navigation.AppNavigator
import com.nolwendroid.core.navigation.NavigationProvider
import com.nolwendroid.feature_movie.MovieScreen
import com.nolwendroid.feature_splash.ui.CineTuneSplashScreen

class NavigationProviderImpl : NavigationProvider {
    override val splashScreen: @Composable (AppNavigator) -> Unit = { navigator ->
        CineTuneSplashScreen(navigator)
    }

    override val movieSelectorScreen: @Composable () -> Unit = {
        MovieScreen()
    }
}