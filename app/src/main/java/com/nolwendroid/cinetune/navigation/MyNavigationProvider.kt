package com.nolwendroid.cinetune.navigation

import androidx.compose.runtime.Composable

interface MyNavigationProvider {
    fun splashScreen(): @Composable () -> Unit
    fun movieSelectorScreen(): @Composable () -> Unit
}