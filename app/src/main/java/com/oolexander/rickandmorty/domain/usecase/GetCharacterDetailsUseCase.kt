package com.oolexander.rickandmorty.domain.usecase

import com.oolexander.rickandmorty.domain.model.CharacterDetails
import com.oolexander.rickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(private val repository: CharacterRepository) {

    suspend operator fun invoke(id: Int): CharacterDetails {
        return repository.getCharacterDetails(id)
    }
}