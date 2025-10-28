package com.oolexander.rickandmorty.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageInfoDto(
    @SerialName("count") val count: Int,
    @SerialName("pages") val pages: Int,
    @SerialName("next") val next: String? = null,
    @SerialName("prev") val prev: String? = null,
)