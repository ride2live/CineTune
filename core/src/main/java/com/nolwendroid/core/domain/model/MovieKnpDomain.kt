package com.nolwendroid.core.domain.model

import com.nolwendroid.core.model.SelectedType

data class MovieKnpDomain(val id: Int,
                          val title: String,
                          val year: String? = null,
                          val genres: List<String>? = null,
                          val rating: String,
                          val posterUrl: String?,
                          val selectedType : SelectedType = SelectedType.NONE)
