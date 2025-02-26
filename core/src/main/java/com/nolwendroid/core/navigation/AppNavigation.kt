package com.nolwendroid.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(
    navController: NavHostController,
    navigator: AppNavigator,
    navigationProvider: NavigationProvider // ✅ Получаем через DI
) {
    NavHost(navController, startDestination = Routes.Splash.route) {
        composable(Routes.Splash.route) { navigationProvider.splashScreen(navigator) }
        composable(Routes.Movie.route) { navigationProvider.movieSelectorScreen() }
    }
}