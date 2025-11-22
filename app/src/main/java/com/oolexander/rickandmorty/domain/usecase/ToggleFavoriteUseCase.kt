package com.oolexander.rickandmorty.domain.usecase

import com.oolexander.rickandmorty.domain.model.CharacterDetails
import com.oolexander.rickandmorty.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) {
    suspend operator fun invoke(character: CharacterDetails) {
        val id = character.id
        val isFav = favoritesRepository.observeIsFavorite(id).first()

        if (isFav) {
            favoritesRepository.removeFromFavorites(id)
        } else {
            favoritesRepository.addToFavorites(character)
        }
    }
}