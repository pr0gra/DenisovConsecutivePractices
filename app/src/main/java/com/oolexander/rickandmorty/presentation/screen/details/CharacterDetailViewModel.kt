package com.oolexander.rickandmorty.presentation.screen.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolexander.rickandmorty.domain.model.CharacterDetails
import com.oolexander.rickandmorty.domain.usecase.GetCharacterDetailsUseCase
import com.oolexander.rickandmorty.domain.usecase.ObserveIsFavoriteUseCase
import com.oolexander.rickandmorty.domain.usecase.ToggleFavoriteUseCase
import com.oolexander.rickandmorty.presentation.screen.details.model.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val observeIsFavoriteUseCase: ObserveIsFavoriteUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel() {

    private val _uiState = mutableStateOf<DetailUiState>(DetailUiState.Loading)
    val uiState: State<DetailUiState> = _uiState

    private var loadedCharacter: CharacterDetails? = null
    private var favObserverJob: Job? = null

    fun loadCharacter(id: Int) {
        _uiState.value = DetailUiState.Loading

        viewModelScope.launch {
            try {
                val details = getCharacterDetailsUseCase(id)
                loadedCharacter = details

                _uiState.value = DetailUiState.Success(
                    character = details,
                    isFavorite = false,
                )

                observeFavoriteStatus(id)
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(
                    message = e.message ?: "Не удалось загрузить персонажа",
                )
            }
        }
    }

    private fun observeFavoriteStatus(id: Int) {
        favObserverJob?.cancel()

        favObserverJob = viewModelScope.launch {
            observeIsFavoriteUseCase(id).collectLatest { isFav ->
                val current = _uiState.value
                if (current is DetailUiState.Success) {
                    _uiState.value = current.copy(isFavorite = isFav)
                }
            }
        }
    }

    fun onFavoriteClick() {
        val data = loadedCharacter ?: return

        viewModelScope.launch {
            toggleFavoriteUseCase(data)
        }
    }
}

