package com.nolwendroid.feature_movie.di

import com.nolwendroid.feature_movie.data.repository.MovieKnpRepository
import com.nolwendroid.feature_movie.data.repository.MovieKnpRepositoryImpl
import com.nolwendroid.feature_movie.domain.usecase.GetPopularMoviesKnpUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieModule {
    @Binds
    @Singleton
    abstract fun bindMovieKnpRepository(
        impl: MovieKnpRepositoryImpl
    ): MovieKnpRepository
}

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetPopularMoviesKnpUseCase(repository: MovieKnpRepository): GetPopularMoviesKnpUseCase {
        return GetPopularMoviesKnpUseCase(repository)
    }
}