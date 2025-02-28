package com.nolwendroid.cinetune.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController, myNavigationProvider: MyNavigationProvider) {
    NavHost(navController, startDestination = Routes.Splash.route) {
        composable(Routes.Splash.route) { myNavigationProvider.splashScreen() }
        composable(Routes.Movie.route) { myNavigationProvider.movieSelectorScreen() }
    }
}