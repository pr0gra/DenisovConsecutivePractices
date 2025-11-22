package com.oolexander.rickandmorty.domain.usecase

import com.oolexander.rickandmorty.domain.model.CharacterFilters
import com.oolexander.rickandmorty.domain.repository.FilterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFiltersUseCase @Inject constructor(
    private val filterRepository: FilterRepository,
) {
    operator fun invoke(): Flow<CharacterFilters> = filterRepository.observeFilters()
}