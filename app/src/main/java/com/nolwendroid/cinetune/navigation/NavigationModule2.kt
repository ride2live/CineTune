package com.nolwendroid.cinetune.navigation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationProviderModule {
    @Binds
    @Singleton
    abstract fun bindNavigationProvider(impl: MyNavigationProviderImpl): MyNavigationProvider

}

