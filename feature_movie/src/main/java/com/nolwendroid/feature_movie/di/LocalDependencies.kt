package com.nolwendroid.feature_movie.di

import com.nolwendroid.feature_movie.data.MovieRepository
import com.nolwendroid.feature_movie.data.MovieRepositoryImpl
import com.nolwendroid.feature_movie.domain.GetPopularMoviesUseCase
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
    abstract fun bindMovieRepository(
        impl: MovieRepositoryImpl
    ): MovieRepository
}