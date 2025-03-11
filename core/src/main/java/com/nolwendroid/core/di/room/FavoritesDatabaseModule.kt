package com.nolwendroid.core.di.room

import android.content.Context
import androidx.room.Room
import com.nolwendroid.core.data.room.FavoritesDataBase
import com.nolwendroid.core.data.room.movies.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoritesDatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FavoritesDataBase {
        return Room.databaseBuilder(
            context,
            FavoritesDataBase::class.java,
            "movies.db"
        ).build()
    }

    @Provides
    fun provideMovieDao(database: FavoritesDataBase): MovieDao {
        return database.movieDao()
    }
}