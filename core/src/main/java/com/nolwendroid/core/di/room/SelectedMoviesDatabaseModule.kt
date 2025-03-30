package com.nolwendroid.core.di.room

import android.content.Context
import androidx.room.Room
import com.nolwendroid.core.data.repository.MovieLocalRepository
import com.nolwendroid.core.data.repository.MovieLocalRepositoryImpl
import com.nolwendroid.core.data.room.CineTuneDataBase
import com.nolwendroid.core.data.room.movies.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SelectedMoviesDatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CineTuneDataBase {
        return Room.databaseBuilder(
            context,
            CineTuneDataBase::class.java,
            "movies.db"
        ).build()
    }

    @Provides
    fun provideMovieDao(database: CineTuneDataBase): MovieDao {
        return database.movieDao()
    }
    @Provides
    fun provideMovieLocalRepository(movieDao: MovieDao): MovieLocalRepository {
        return MovieLocalRepositoryImpl(movieDao)
    }
}