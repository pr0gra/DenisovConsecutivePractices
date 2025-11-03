package com.oolexander.rickandmorty.data.datasource.local

import com.oolexander.rickandmorty.data.models.entity.FavoriteCharacterEntity
import javax.inject.Inject

class FavoritesLocalDataSource @Inject constructor(private val dao: FavoriteCharacterDao) {
    suspend fun upsertFavorite(entity: FavoriteCharacterEntity) = dao.upsert(entity)

    fun observeFavorites() = dao.observeAll()
    fun observeIsFavorite(id: Int) = dao.observeIsFavorite(id)

    suspend fun getFavoriteById(id: Int) = dao.getById(id)
    suspend fun deleteFavorite(id: Int) = dao.deleteById(id)
}