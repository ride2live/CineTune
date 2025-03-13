package com.nolwendroid.feature_movie.data.repository

import android.graphics.pdf.LoadParams
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nolwendroid.core.di.network.RequestWrapper
import com.nolwendroid.core.di.network.ResultState
import com.nolwendroid.feature_movie.api.KinopoiskApiService
import com.nolwendroid.feature_movie.domain.mappers.toDomain
import com.nolwendroid.feature_movie.domain.model.MovieKnpDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieKnpRepositoryImpl @Inject constructor(
    private val api: KinopoiskApiService, private val requestWrapper: RequestWrapper
) : MovieKnpRepository {
    init {
        Log.d("Hilt-Debug", "✅ MovieKnpRepositoryImpl успешно создан")
    }

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
    override fun searchMovies(query: String): Flow<ResultState<List<MovieKnpDomain>>> = flow {
      emitAll(
            requestWrapper.sendRequest(
                apiMethod = { api.searchMovies(query) },
                mapToDomain = { response ->
                    println( response.movies)
                    response.movies.map { it.toDomain() }
                })
      )
    }


}

