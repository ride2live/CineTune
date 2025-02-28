package com.nolwendroid.feature_movie.domain.mappers

import com.nolwendroid.feature_movie.data.model.MovieDTO
import com.nolwendroid.feature_movie.domain.model.MovieDomain
import com.nolwendroid.feature_movie.ui.model.MovieUi

//No need to use Mapper class - no Di or Context needed
// DTO to Domain
fun MovieDTO.toDomain(): MovieDomain {
    return MovieDomain(
        id = id,
        title = title,
        overview = overview,
        posterUrl = "https://image.tmdb.org/t/p/w500/$posterPath",
        releaseDate = releaseDate ?: "Неизвестно",
        rating = voteAverage
    )
}

// Domain to UI
fun MovieDomain.toUi(): MovieUi {
    return MovieUi(
        id = id,
        title = title,
        description = overview,
        imageUrl = posterUrl,
        rating = "%.1f / 10".format(rating)
    )
}