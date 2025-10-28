package com.oolexander.rickandmorty.presentation.screen.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolexander.rickandmorty.domain.model.CharacterDetails
import com.oolexander.rickandmorty.domain.usecase.GetCharacterDetailsUseCase
import com.oolexander.rickandmorty.presentation.screen.details.model.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
) : ViewModel() {

    private val _uiState = mutableStateOf<DetailUiState>(DetailUiState.Loading)
    val uiState: State<DetailUiState> = _uiState

    fun loadCharacter(id: Int) {
        _uiState.value = DetailUiState.Loading

        viewModelScope.launch {
            try {
                val details: CharacterDetails = getCharacterDetailsUseCase(id)
                _uiState.value = DetailUiState.Success(details)
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(
                    message = e.message ?: "Не удалось загрузить персонажа"
                )
            }
        }
    }
}

