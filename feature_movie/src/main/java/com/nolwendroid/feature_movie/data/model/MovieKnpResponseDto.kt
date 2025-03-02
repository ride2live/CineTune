package com.nolwendroid.feature_movie.data.model

import com.google.gson.annotations.SerializedName

data class MovieKnpResponseDto(
    val total: Int,
    val totalPages: Int,
    val items: List<MovieKnpDto> // ✅ Исправлено `films` → `items`
)

data class MovieKnpDto(
    @SerializedName("kinopoiskId") val filmId: Int,
    val nameRu: String?,
    val nameEn: String?,
    val nameOriginal: String?,
    val year: String?,
    val genres: List<GenreDto>?,
    @SerializedName("ratingKinopoisk") val rating: Double?,
    val posterUrl: String?
)

data class GenreDto(
    val genre: String?
)
