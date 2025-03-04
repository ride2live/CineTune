package com.nolwendroid.core.di.network

sealed class ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error(val message: String) : ResultState<Nothing>()
    data object Loading : ResultState<Nothing>()
}