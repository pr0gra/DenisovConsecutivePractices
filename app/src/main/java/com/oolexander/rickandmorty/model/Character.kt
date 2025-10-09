package com.oolexander.rickandmorty.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: CharacterGender,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

@Serializable
data class Location(
    val name: String,
    val url: String
)

@Serializable
enum class CharacterStatus(val rusName: String) {
    @SerialName("Alive")
    ALIVE("Жив"),

    @SerialName("Dead")
    DEAD("Мертв"),

    @SerialName("unknown")
    UNKNOWN("Неизвестно");

    companion object {
        fun fromString(value: String): CharacterStatus {
            return when (value) {
                "Alive" -> ALIVE
                "Dead" -> DEAD
                "unknown" -> UNKNOWN
                else -> UNKNOWN
            }
        }
    }
}

@Serializable
enum class CharacterGender(val rusName: String) {
    @SerialName("Female")
    FEMALE("Женский"),

    @SerialName("Male")
    MALE("Мужской"),

    @SerialName("Genderless")
    GENDERLESS("Без пола"),

    @SerialName("unknown")
    UNKNOWN("Неизвестно");

    companion object {
        fun fromString(value: String): CharacterGender {
            return when (value) {
                "Female" -> FEMALE
                "Male" -> MALE
                "Genderless" -> GENDERLESS
                "unknown" -> UNKNOWN
                else -> UNKNOWN
            }
        }
    }
}