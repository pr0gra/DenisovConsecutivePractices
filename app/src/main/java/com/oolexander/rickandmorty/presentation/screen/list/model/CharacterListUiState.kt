package com.oolexander.rickandmorty.presentation.screen.list.model

import com.oolexander.rickandmorty.domain.model.Character
import com.oolexander.rickandmorty.domain.model.CharacterGender
import com.oolexander.rickandmorty.domain.model.CharacterStatus

data class CharacterListUiState(
    val characters: List<Character> = emptyList(),

    val searchText: String? = null,
    val speciesFilter: String? = null,
    val genderFilter: CharacterGender? = null,
    val statusFilter: CharacterStatus? = null,

    val currentPage: Int = 1,
    val isLoading: Boolean = false,
    val isLoadingNextPage: Boolean = false,
    val endReached: Boolean = false,

    val error: String? = null,
) {
    val showEmptyState: Boolean
        get() = characters.isEmpty() && !isLoading && error == null

    val showLoading: Boolean
        get() = isLoading && characters.isEmpty()

    val showContent: Boolean
        get() = characters.isNotEmpty()

    val showError: Boolean
        get() = error != null && characters.isEmpty()
}