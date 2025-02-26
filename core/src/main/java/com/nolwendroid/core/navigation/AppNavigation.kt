package com.nolwendroid.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navigator: AppNavigator, navController: NavHostController, navigationProvider: NavigationProvider) {
    NavHost(navController, startDestination = Routes.Splash.route) {
        composable(Routes.Splash.route) { navigationProvider.splashScreen(navigator) }
        composable(Routes.Movie.route) { navigationProvider.movieSelectorScreen() }
    }
}