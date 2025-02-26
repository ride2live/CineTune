package com.nolwendroid.core.navigation

sealed class Routes(val route: String) {
    data object Splash : Routes("splash")
    data object Movie : Routes("movie")
}