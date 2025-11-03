package com.oolexander.rickandmorty.domain.model

data class Character(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val gender: CharacterGender,
    val location: Location,
    val image: String,
)