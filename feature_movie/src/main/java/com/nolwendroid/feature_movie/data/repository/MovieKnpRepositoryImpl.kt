package com.nolwendroid.feature_movie.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nolwendroid.core.data.room.movies.MovieDao
import com.nolwendroid.core.data.room.movies.MovieEntity
import com.nolwendroid.core.di.network.RequestWrapper
import com.nolwendroid.feature_movie.api.KinopoiskApiService
import com.nolwendroid.feature_movie.domain.mappers.toDomain
import com.nolwendroid.core.domain.model.MovieKnpDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieKnpRepositoryImpl @Inject constructor(
    private val api: KinopoiskApiService, private val requestWrapper: RequestWrapper,
    private val movieDao: MovieDao
) : MovieKnpRepository {
    init {
        println ("$movieDao - movieDao" )
    }
    //TODO one method with pager wrapping
    override fun getPopularMovies(): PagingSource<Int, MovieKnpDomain> {
        return object : PagingSource<Int, MovieKnpDomain>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieKnpDomain> {
                return try {
                    val page = params.key ?: 1
                    val response = api.getPopularMovies(type = "TOP_250_MOVIES", page = page)

                    LoadResult.Page(
                        data = response.items.map { it.toDomain() },
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (response.items.isNotEmpty()) page + 1 else null
                    )
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }

            override fun getRefreshKey(state: PagingState<Int, MovieKnpDomain>): Int? {
                return state.anchorPosition?.let { position ->
                    val page = state.closestPageToPosition(position)
                    page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
                }
            }
        }
    }
    override fun searchMovies(query: String): PagingSource<Int, MovieKnpDomain> {
        return object : PagingSource<Int, MovieKnpDomain>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieKnpDomain> {
                return try {
                    val page = params.key ?: 1
                    val response = api.searchMovies(query, page)
                    LoadResult.Page(
                        data = response.movies.map { it.toDomain() },
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (response.movies.isNotEmpty()) page + 1 else null
                    )
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }

            override fun getRefreshKey(state: PagingState<Int, MovieKnpDomain>): Int? {
                return state.anchorPosition?.let { position ->
                    val page = state.closestPageToPosition(position)
                    page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
                }
            }
        }
    }
    fun getSelectedMovies(): Flow<List<MovieEntity>> {
        return movieDao.getFavoriteMovies()
    }

    // Добавить фильм в избранное
    suspend fun addMovie(movie: MovieEntity) {
        movieDao.addMovie(movie)
    }
    suspend fun addMovies(movie: List<MovieEntity>) {
        movieDao.addMovieList(movie)
    }

    // Удалить фильм из избранного
    suspend fun removeMovie(movie: MovieEntity) {
        movieDao.removeMovie(movie)
    }

    // Очистить список избранных фильмов
    suspend fun clearSelectedMovies() {
        //movieDao.clearMovies()
    }

}

