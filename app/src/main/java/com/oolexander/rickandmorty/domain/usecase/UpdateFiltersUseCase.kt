package com.oolexander.rickandmorty.domain.usecase

import com.oolexander.rickandmorty.domain.model.CharacterFilters
import com.oolexander.rickandmorty.domain.repository.FilterRepository
import javax.inject.Inject

class UpdateFiltersUseCase @Inject constructor(private val filterRepository: FilterRepository) {
    suspend fun set(filters: CharacterFilters) {
        filterRepository.setFilters(filters)
    }

    suspend fun clear() {
        filterRepository.clearFilters()
    }
}