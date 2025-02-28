package com.nolwendroid.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController, navigationProvider: NavigationProvider) {
    NavHost(navController, startDestination = Routes.Splash.route) {
        composable(Routes.Splash.route) { navigationProvider.splashScreen() }
        composable(Routes.Movie.route) { navigationProvider.movieSelectorScreen() }
    }
}