package com.nolwendroid.feature_movie.domain.model

data class MovieKnpDomain(val id: Int,
                          val title: String,
                          val year: String?,
                          val genres: List<String>?,
                          val rating: String,
                          val posterUrl: String?)
