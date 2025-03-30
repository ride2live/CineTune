package com.nolwendroid.core.di.usecases

import com.nolwendroid.core.data.repository.MovieLocalRepository
import com.nolwendroid.core.domain.usecases.AddSelectedMoviesUseCase
import com.nolwendroid.core.domain.usecases.GetSelectedMoviesUseCase
import com.nolwendroid.core.domain.usecases.RemoveSelectedMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideAddSelectedMoviesUseCase(repository: MovieLocalRepository): AddSelectedMoviesUseCase {
        return AddSelectedMoviesUseCase(repository)
    }
    @Provides
    fun provideRemoveSelectedMoviesUseCase(repository: MovieLocalRepository): RemoveSelectedMoviesUseCase {
        return RemoveSelectedMoviesUseCase(repository)
    }
    @Provides
    fun provideGetSelectedMoviesUseCase(repository: MovieLocalRepository): GetSelectedMoviesUseCase {
        return GetSelectedMoviesUseCase(repository)
    }

}