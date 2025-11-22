package com.oolexander.rickandmorty.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersPageDto(
    @SerialName("info") val info: PageInfoDto,
    @SerialName("results") val results: List<CharacterDto>,
)