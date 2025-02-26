package com.nolwendroid.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.Navigator

interface NavigationProvider {
    val splashScreen: @Composable (AppNavigator) -> Unit
    val movieSelectorScreen: @Composable () -> Unit
}