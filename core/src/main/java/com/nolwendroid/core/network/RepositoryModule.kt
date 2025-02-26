package com.nolwendroid.core.di


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
//    @Binds
//    abstract fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
//    @Binds
//    abstract fun provideUserMusicRepository(userMusicRepository: UserMusicRepositoryImpl): UserMusicRepository
//    @Binds
//    abstract fun provideRequestBase(baseRepo: RequestWrapperImpl): RequestWrapper


}