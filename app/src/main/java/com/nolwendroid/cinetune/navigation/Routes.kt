package com.nolwendroid.cinetune.navigation

sealed class Routes(val route: String) {
    data object Splash : Routes("splash")
    data object Movie : Routes("movie")
}