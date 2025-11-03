package com.oolexander.rickandmorty.domain.usecase

import com.oolexander.rickandmorty.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveIsFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) {
    operator fun invoke(id: Int): Flow<Boolean> {
        return favoritesRepository.observeIsFavorite(id)
    }
}