package com.oolexander.rickandmorty.data.repository

import com.oolexander.rickandmorty.data.datasource.local.FavoritesLocalDataSource
import com.oolexander.rickandmorty.data.mappers.toDomain
import com.oolexander.rickandmorty.data.mappers.toEntity
import com.oolexander.rickandmorty.data.mappers.toReducedDomain
import com.oolexander.rickandmorty.domain.model.Character
import com.oolexander.rickandmorty.domain.model.CharacterDetails
import com.oolexander.rickandmorty.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepositoryImpl @Inject constructor(
    private val local: FavoritesLocalDataSource,
) : FavoritesRepository {
    override fun observeFavorites(): Flow<List<Character>> {
        return local.observeFavorites().map { entities ->
            entities.map { it.toReducedDomain() }
        }
    }

    override fun observeIsFavorite(id: Int): Flow<Boolean> {
        return local.observeIsFavorite(id)
    }

    override suspend fun addToFavorites(character: CharacterDetails) {
        local.upsertFavorite(character.toEntity())
    }

    override suspend fun removeFromFavorites(id: Int) { local.deleteFavorite(id) }

    override suspend fun getFavoriteById(id: Int): CharacterDetails? {
        return local.getFavoriteById(id)?.toDomain()
    }
}