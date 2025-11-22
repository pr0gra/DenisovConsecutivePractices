package com.oolexander.rickandmorty.domain.usecase

import com.oolexander.rickandmorty.domain.model.Character
import com.oolexander.rickandmorty.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(): Flow<List<Character>> {
        return favoritesRepository.observeFavorites()
    }
}