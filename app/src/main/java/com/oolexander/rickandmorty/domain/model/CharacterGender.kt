package com.oolexander.rickandmorty.domain.model

enum class CharacterGender(
    val rusName: String,
    val engName: String,
) {
    FEMALE("Женский", "Female"),
    MALE("Мужской", "Male"),
    GENDERLESS("Без пола", "Genderless"),
    UNKNOWN("Неизвестно", "unknown");

    companion object {
        fun fromEng(value: String?): CharacterGender {
            return entries.find { it.engName.equals(value, ignoreCase = true) } ?: UNKNOWN
        }
    }
}