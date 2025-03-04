package com.nolwendroid.core.di.network

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RequestWrapper {
    suspend fun <T, R> sendRequest(
        apiMethod: suspend () -> Response<T>,
        mapToDomain: (T) -> R
    ): Flow<ResultState<R>>
}