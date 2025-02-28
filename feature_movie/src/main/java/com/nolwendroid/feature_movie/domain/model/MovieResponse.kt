package com.nolwendroid.feature_movie.domain.model

import com.google.gson.annotations.SerializedName
import com.nolwendroid.feature_movie.data.model.MovieDTO

data class MovieResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDTO>, // ✅ API отдаёт список фильмов в `results`
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)