package com.nolwendroid.feature_movie.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDomain(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val releaseDate: String,
    val rating: Float
)
