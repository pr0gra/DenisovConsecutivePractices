package com.oolexander.rickandmorty.ui.screen.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolexander.rickandmorty.data.CharacterRepository
import com.oolexander.rickandmorty.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {
    private val _uiState = mutableStateOf<DetailUiState>(DetailUiState.Loading)
    val uiState: State<DetailUiState> = _uiState

    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            delay(500)
            val character =
                repository.mockCharacters.find { it.id == id } ?: repository.mockCharacters[0]
            _uiState.value = DetailUiState.Success(character)
        }
    }
}

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val character: Character) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}