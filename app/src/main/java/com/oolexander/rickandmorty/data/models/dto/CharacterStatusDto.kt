package com.oolexander.rickandmorty.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class CharacterStatusDto(
    val rusName: String,
    val engName: String,
) {
    @SerialName("Alive")
    ALIVE("Жив", "Alive"),

    @SerialName("Dead")
    DEAD("Мёртв", "Dead"),

    @SerialName("unknown")
    UNKNOWN("Неизвестно", "unknown");
}