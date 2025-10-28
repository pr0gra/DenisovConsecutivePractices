package com.oolexander.rickandmorty.data.models.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class CharacterGenderDto(
    val rusName: String,
    val engName: String,
) {
    @SerialName("Female")
    FEMALE("Женский", "Female"),

    @SerialName("Male")
    MALE("Мужской", "Male"),

    @SerialName("Genderless")
    GENDERLESS("Без пола", "Genderless"),

    @SerialName("unknown")
    UNKNOWN("Неизвестно", "unknown");
}