package com.nolwendroid.di.navigation


import com.nolwendroid.cinetune.navigation.NavigationProviderImpl
import com.nolwendroid.core.navigation.AppNavigator
import com.nolwendroid.core.navigation.NavigationProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationProviderModule {
    @Binds
    @Singleton
    abstract fun bindNavigationProvider(impl: NavigationProviderImpl): NavigationProvider

}

