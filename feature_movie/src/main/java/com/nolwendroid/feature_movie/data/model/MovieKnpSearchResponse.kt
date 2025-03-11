package com.nolwendroid.feature_movie.data.model

import com.google.gson.annotations.SerializedName

data class MovieKnpSearchResponse (
    @SerializedName("films") val movies: List<MovieSearchKnpDto>,
    @SerializedName("pagesCount") val pagesCount: Int,
    @SerializedName("searchFilmsCountResult") val searchFilmsCountResult: Int
)

data class MovieSearchKnpDto(
    @SerializedName("filmId") val filmId: Int,
    val nameRu: String?,
    val nameEn: String?,
    val year: String?,
    val genres: List<GenreDto>?,
    @SerializedName("rating") val rating: String?,
    @SerializedName("posterUrlPreview") val posterUrl: String?,

)