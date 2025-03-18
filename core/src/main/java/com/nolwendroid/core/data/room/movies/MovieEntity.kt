package com.nolwendroid.core.data.room.movies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selected_movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val rating: String,
    val year: String,
    val posterUrl: String?,
    val isLiked: Boolean
)