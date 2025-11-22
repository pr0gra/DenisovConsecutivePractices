package com.oolexander.rickandmorty.presentation.screen.filters.model

import com.oolexander.rickandmorty.domain.model.CharacterFilters
import com.oolexander.rickandmorty.domain.model.CharacterGender
import com.oolexander.rickandmorty.domain.model.CharacterStatus

data class FiltersViewState(
    val status: CharacterStatus? = null,
    val gender: CharacterGender? = null,
    val species: String = "",
    val isLoading: Boolean = true,
    val error: String? = null,
) {
    val showLoading: Boolean
        get() = isLoading

    val showError: Boolean
        get() = !isLoading && error != null

    val showContent: Boolean
        get() = !isLoading && error == null

    fun toDomain(): CharacterFilters {
        return CharacterFilters(
            species = species.ifBlank { null },
            status = status,
            gender = gender,
        )
    }

    companion object {
        fun fromDomain(filters: CharacterFilters): FiltersViewState {
            return FiltersViewState(
                status = filters.status,
                gender = filters.gender,
                species = filters.species.orEmpty(),
                isLoading = false,
                error = null
            )
        }
    }
}
