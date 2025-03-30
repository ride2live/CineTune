package com.nolwendroid.core.data.repository

import com.nolwendroid.core.data.room.movies.MovieDao
import com.nolwendroid.core.data.room.movies.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieLocalRepository {
    override fun getSelectedMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    override suspend fun addMovie(movie: MovieEntity) = movieDao.addMovie(movie)

    override suspend fun addMovies(movies: List<MovieEntity>) = movieDao.addMovieList(movies)

    override suspend fun removeMovie(movie: MovieEntity) = movieDao.removeMovie(movie)

    //override suspend fun clearSelectedMovies() = movieDao.clearMovies()
}