package com.oolexander.rickandmorty.domain.repository

import com.oolexander.rickandmorty.domain.model.CharacterFilters
import kotlinx.coroutines.flow.Flow

interface FilterRepository {
    fun observeFilters(): Flow<CharacterFilters>
    suspend fun setFilters(filters: CharacterFilters)
    suspend fun clearFilters()
}