package com.nolwendroid.cinetune.di.navigation

sealed class Routes(val route: String) {
    data object Splash : Routes("splash")
    data object Movie : Routes("movie")
}