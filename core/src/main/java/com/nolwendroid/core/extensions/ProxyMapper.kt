package com.nolwendroid.core.extensions

import com.nolwendroid.core.di.network.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <T, R> Flow<ResultState<T>>.mapResultState(transform: (T) -> R): Flow<ResultState<R>> {
    return map { result ->
        when (result) {
            is ResultState.Success -> ResultState.Success(transform(result.data))
            is ResultState.Error -> result
            is ResultState.Loading -> result
        }
    }
}