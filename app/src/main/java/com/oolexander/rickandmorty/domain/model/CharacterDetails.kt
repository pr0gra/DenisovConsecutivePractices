package com.oolexander.rickandmorty.domain.model

data class CharacterDetails(
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
    val created: String,
)