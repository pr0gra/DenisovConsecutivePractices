package com.oolexander.rickandmorty.presentation.screen.details.model

import com.oolexander.rickandmorty.domain.model.CharacterDetails

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val character: CharacterDetails) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}