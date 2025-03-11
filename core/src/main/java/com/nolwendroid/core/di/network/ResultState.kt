package com.nolwendroid.core.di.network

sealed class ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error(val message: String) : ResultState<Nothing>()
    data object Loading : ResultState<Nothing>()
    data object Idle : ResultState<Nothing>()
}

fun <T> ResultState<List<T>>.orEmpty(): List<T> {
    return if (this is ResultState.Success) data.orEmpty() else emptyList()
}