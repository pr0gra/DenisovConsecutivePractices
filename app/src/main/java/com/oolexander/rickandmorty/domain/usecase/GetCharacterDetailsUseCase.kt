package com.oolexander.rickandmorty.domain.usecase

import com.oolexander.rickandmorty.domain.model.CharacterDetails
import com.oolexander.rickandmorty.domain.repository.CharacterRepository
import com.oolexander.rickandmorty.domain.repository.FavoritesRepository
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val favoritesRepository: FavoritesRepository,
) {
    suspend operator fun invoke(id: Int): CharacterDetails {
        val local = favoritesRepository.getFavoriteById(id)
        if (local != null) return local

        return characterRepository.getCharacterDetails(id)
    }
}