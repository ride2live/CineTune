package com.nolwendroid.core.navigation

import androidx.compose.runtime.Composable

interface NavigationProvider {
    fun splashScreen(): @Composable () -> Unit
    fun movieSelectorScreen(): @Composable () -> Unit
}