package com.oolexander.rickandmorty.domain.model

enum class CharacterStatus(
    val rusName: String,
    val engName: String,
) {
    ALIVE("Жив", "Alive"),
    DEAD("Мёртв", "Dead"),
    UNKNOWN("Неизвестно", "unknown");

    companion object {
        fun fromEng(value: String?): CharacterStatus {
            return entries.find { it.engName.equals(value, ignoreCase = true) } ?: UNKNOWN
        }
    }
}