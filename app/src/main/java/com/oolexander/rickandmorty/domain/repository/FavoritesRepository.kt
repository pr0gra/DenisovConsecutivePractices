package com.oolexander.rickandmorty.domain.repository

import com.oolexander.rickandmorty.domain.model.Character
import com.oolexander.rickandmorty.domain.model.CharacterDetails
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun observeFavorites(): Flow<List<Character>>
    fun observeIsFavorite(id: Int): Flow<Boolean>

    suspend fun addToFavorites(character: CharacterDetails)
    suspend fun removeFromFavorites(id: Int)

    suspend fun getFavoriteById(id: Int): CharacterDetails?
}