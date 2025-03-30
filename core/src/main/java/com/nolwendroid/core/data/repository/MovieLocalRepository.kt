package com.nolwendroid.core.data.repository

import com.nolwendroid.core.data.room.movies.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieLocalRepository {
    fun getSelectedMovies(): Flow<List<MovieEntity>>
    suspend fun addMovie(movie: MovieEntity)
    suspend fun addMovies(movies: List<MovieEntity>)
    suspend fun removeMovie(movie: MovieEntity)
   // suspend fun clearSelectedMovies()
}