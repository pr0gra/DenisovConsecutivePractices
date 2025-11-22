package com.oolexander.rickandmorty.domain.repository

import com.oolexander.rickandmorty.domain.model.Character
import com.oolexander.rickandmorty.domain.model.CharacterDetails

interface CharacterRepository {

    suspend fun getCharacters(
        page: Int? = null,
        name: String? = null,
        status: String? = null,
        species: String? = null,
        gender: String? = null,
    ): List<Character>

    suspend fun getCharacterDetails(id: Int): CharacterDetails
}