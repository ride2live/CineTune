package com.nolwendroid.feature_movie.domain.mappers

import com.nolwendroid.core.data.room.movies.MovieEntity
import com.nolwendroid.core.model.MovieKnpUi
import com.nolwendroid.core.model.SelectedType
import com.nolwendroid.feature_movie.data.model.MovieKnpDto
import com.nolwendroid.feature_movie.data.model.MovieSearchKnpDto
import com.nolwendroid.feature_movie.domain.model.MovieDomain
import com.nolwendroid.core.domain.model.MovieKnpDomain
import com.nolwendroid.feature_movie.ui.model.MovieUi

//No need to use Mapper class - no Di or Context needed
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
        posterUrl = posterUrl,
        )
}

fun MovieKnpUi.toDomain(): MovieKnpDomain {
    return MovieKnpDomain(
        id = id,
        title = title.substringBeforeLast(" (${year})"), // Убираем год из названия
        rating = rating.takeIf { it != "N/A" }?.toDoubleOrNull().toString(),
        year = (year.toIntOrNull() ?: 0).toString(), // Если парсинг не удался, подставляем 0
        posterUrl = posterUrl,
        selectedType = selectedType
    )
}
//Todo fix genres
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

fun MovieSearchKnpDto.toDomain(): MovieKnpDomain {
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