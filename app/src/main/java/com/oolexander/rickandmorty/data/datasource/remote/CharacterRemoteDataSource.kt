package com.oolexander.rickandmorty.data.datasource.remote

import com.oolexander.rickandmorty.data.models.dto.CharacterDto
import com.oolexander.rickandmorty.data.models.dto.CharactersPageDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRemoteDataSource @Inject constructor(private val api: RickAndMortyApi) {

    suspend fun getCharactersPage(
        page: Int? = null,
        name: String? = null,
        status: String? = null,
        species: String? = null,
        gender: String? = null,
    ): CharactersPageDto {
        return api.getCharacters(
            page = page,
            name = name,
            status = status,
            species = species,
            gender = gender,
        )
    }

    suspend fun getCharacterById(id: Int): CharacterDto {
        return api.getCharacterById(id)
    }
}