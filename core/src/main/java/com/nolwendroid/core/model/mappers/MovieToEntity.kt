package com.nolwendroid.core.model.mappers

import com.nolwendroid.core.data.room.movies.MovieEntity
import com.nolwendroid.core.domain.model.MovieKnpDomain
import com.nolwendroid.core.model.SelectedType

fun MovieKnpDomain.toEntity(): MovieEntity {
    return MovieEntity(
        id = this.id,
        title = this.title,
        rating = this.rating,
        year = this.year,
        posterUrl = this.posterUrl,
        isLiked =
        when (selectedType) {
            SelectedType.FAVORITE -> true
            SelectedType.DISLIKED -> false
            SelectedType.NONE -> false
        }
    )
}