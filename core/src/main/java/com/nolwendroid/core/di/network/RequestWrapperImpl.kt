package com.nolwendroid.core.di.network

import android.util.Log
import com.nolwendroid.core.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RequestWrapperImpl @Inject constructor()
: RequestWrapper {

    override suspend fun <T,R> sendRequest(apiMethod: suspend () -> Response<T>, mapToDomain: (T) -> R): Flow<ResultState<R>> = flow {
        emit(ResultState.Loading)
        try {
            val response = apiMethod()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(ResultState.Success(mapToDomain(body)))
                } else {
                    emit(ResultState.Error("Empty response body"))
                }
            } else {
                emit(ResultState.Error("HTTP Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Log.e("API_ERROR", "Ошибка при вызове API", e)
            emit(handleException(e))
        }
    }

    private fun handleException(e: Exception): ResultState.Error {
        return when (e) {
            is HttpException -> ResultState.Error("HTTP Error: ${e.code()} ${e.message()}")
            is IOException -> ResultState.Error("Network error: check your connection")
            else -> ResultState.Error("Unexpected error: ${e.localizedMessage}")
        }
    }
}