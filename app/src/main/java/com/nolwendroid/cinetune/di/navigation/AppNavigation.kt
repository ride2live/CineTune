package com.nolwendroid.cinetune.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nolwendroid.cinetune.di.navigation.AppNavigator
import com.nolwendroid.cinetune.di.navigation.Routes
import com.nolwendroid.feature_movie.ui.MovieSelectorScreen
import com.nolwendroid.feature_splash.ui.CineTuneSplashScreen

@Composable
fun AppNavigation(navController: NavHostController, appNavigator: AppNavigator) {
    appNavigator.setNavController(navController)
    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route
    ) {
        composable(Routes.Splash.route) {
            CineTuneSplashScreen {
                navController.navigate(Routes.Movie.route){
                    popUpTo(Routes.Splash.route) { inclusive = true }
                }
            }
        }
        composable(Routes.Movie.route) { MovieSelectorScreen() }
    }
}
