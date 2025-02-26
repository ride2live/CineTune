package com.nolwendroid.core.navigation

import androidx.navigation.NavController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNavigator @Inject constructor() {
    private var navController: NavController? = null

    fun setNavController(controller: NavController) {
        navController = controller
    }

    fun navigateToMovie() {
        navController?.navigate(Routes.Movie.route) {
            popUpTo(Routes.Splash.route) { inclusive = true }
        }
    }

    fun navigateBack() {
        navController?.popBackStack()
    }
}