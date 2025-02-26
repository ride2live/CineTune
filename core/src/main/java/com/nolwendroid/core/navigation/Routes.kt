package com.nolwendroid.core.navigation

sealed class Routes(val route: String) {
    data object Splash : Routes("splash")  // ✅ Экран загрузки
    data object Movie : Routes("movie")    // ✅ Экран списка фильмов
}