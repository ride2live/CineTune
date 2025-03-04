package com.nolwendroid.cinetune.navigation

import android.os.Build
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
    //api 31 default splash
    val startDestination =
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) Routes.Splash.route else Routes.Movie.route

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.Splash.route) {
            CineTuneSplashScreen {
                navController.navigate(Routes.Movie.route) {
                    popUpTo(Routes.Splash.route) { inclusive = true }
                }
            }
        }
        composable(Routes.Movie.route) { MovieSelectorScreen() }
    }
}
