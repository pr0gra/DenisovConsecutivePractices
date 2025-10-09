package com.oolexander.rickandmorty.ui.screen.list

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
class CharacterListViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {
    private val _uiState = mutableStateOf<ListUiState>(ListUiState.Loading)
    val uiState: State<ListUiState> = _uiState

    init {
        viewModelScope.launch {
            loadCharacters()
        }
    }

    private suspend fun loadCharacters() {
        delay(1000)
        _uiState.value = ListUiState.Success(repository.mockCharacters)
    }
}

sealed class ListUiState {
    object Loading : ListUiState()
    data class Success(val characters: List<Character>) : ListUiState()
    data class Error(val message: String) : ListUiState()
}