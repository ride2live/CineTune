package com.nolwendroid.feature_movie.domain.mappers

import com.nolwendroid.feature_movie.data.model.MovieDTO
import com.nolwendroid.feature_movie.data.model.MovieKnpDto
import com.nolwendroid.feature_movie.domain.model.MovieDomain
import com.nolwendroid.feature_movie.domain.model.MovieKnpDomain
import com.nolwendroid.feature_movie.ui.model.MovieKnpUi
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

// Domain to UI
fun MovieKnpDomain.toUi(): MovieKnpUi {
    return MovieKnpUi(
        id = id,
        title = "$title (${year})",
        rating = rating?.toString() ?: "N/A",
        year = year.toString(),
        posterUrl = posterUrl
    )
}

fun MovieKnpDto.toDomain(): MovieKnpDomain {
    return MovieKnpDomain(
        id = filmId,
        title = nameRu ?: nameEn ?: "Без названия",
        year = year,
        genres = null,
        //genres = genres.map { it.genre?:"Нет жанра" },
        rating = rating.toString() ?: "N/A",
        posterUrl = posterUrl
    )
}