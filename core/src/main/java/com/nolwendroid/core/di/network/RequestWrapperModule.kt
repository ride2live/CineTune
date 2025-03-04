package com.nolwendroid.core.di.network

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RequestWrapperModule {
    @Binds
    @Singleton
    abstract fun bindRequestWrapper(
        impl: RequestWrapperImpl
    ): RequestWrapper
}