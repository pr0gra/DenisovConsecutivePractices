package com.oolexander.rickandmorty.presentation.screen.favorites.model

import com.oolexander.rickandmorty.domain.model.Character

data class FavoritesViewState(
    val favorites: List<Character> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
) {
    val showLoading: Boolean
        get() = isLoading && favorites.isEmpty() && error == null

    val showEmptyState: Boolean
        get() = favorites.isEmpty() && !isLoading && error == null

    val showError: Boolean
        get() = error != null && favorites.isEmpty()

    val showContent: Boolean
        get() = favorites.isNotEmpty()
}