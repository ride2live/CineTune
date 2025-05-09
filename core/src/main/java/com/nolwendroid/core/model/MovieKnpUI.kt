package com.nolwendroid.core.model

data class MovieKnpUi(
    val id: Int,
    val title: String,
    val rating: String,
    val year: String,
    val posterUrl: String?,
    val isSelected: Boolean = false,
    var selectedType : SelectedType = SelectedType.NONE
)

enum class SelectedType {
    FAVORITE,
    DISLIKED,
    NONE
}