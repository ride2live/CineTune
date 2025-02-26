package com.nolwendroid.di.navigation


import com.nolwendroid.core.navigation.AppNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Provides
    @Singleton
    fun provideNavigator(): AppNavigator = AppNavigator()
}