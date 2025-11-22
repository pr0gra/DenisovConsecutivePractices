package com.oolexander.rickandmorty.domain.model

data class CharacterFilters(
    val status: CharacterStatus? = null,
    val species: String? = null,
    val gender: CharacterGender? = null,
)