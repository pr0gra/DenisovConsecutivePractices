package com.oolexander.rickandmorty.domain.usecase

import com.oolexander.rickandmorty.domain.model.Character
import com.oolexander.rickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharacterRepository) {

    suspend operator fun invoke(
        page: Int? = null,
        name: String? = null,
        status: String? = null,
        species: String? = null,
        gender: String? = null,
    ): List<Character> {
        return repository.getCharacters(
            page = page,
            name = name,
            status = status,
            species = species,
            gender = gender
        )
    }
}