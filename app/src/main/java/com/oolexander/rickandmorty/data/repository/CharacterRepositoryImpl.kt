package com.oolexander.rickandmorty.data.repository

import com.oolexander.rickandmorty.data.datasource.remote.CharacterRemoteDataSource
import com.oolexander.rickandmorty.data.mappers.toDomain
import com.oolexander.rickandmorty.data.mappers.toReducedDomain
import com.oolexander.rickandmorty.domain.model.Character
import com.oolexander.rickandmorty.domain.model.CharacterDetails
import com.oolexander.rickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val remote: CharacterRemoteDataSource,
) : CharacterRepository {

    override suspend fun getCharacters(
        page: Int?,
        name: String?,
        status: String?,
        species: String?,
        gender: String?,
    ): List<Character> {
        val response = remote.getCharactersPage(
            page = page,
            name = name,
            status = status,
            species = species,
            gender = gender,
        )

        return response.results.map { it.toReducedDomain() }
    }

    override suspend fun getCharacterDetails(id: Int): CharacterDetails {
        return remote.getCharacterById(id).toDomain()
    }
}