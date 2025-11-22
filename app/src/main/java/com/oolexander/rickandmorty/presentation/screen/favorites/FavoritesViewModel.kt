package com.oolexander.rickandmorty.presentation.screen.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolexander.rickandmorty.domain.usecase.ObserveFavoritesUseCase
import com.oolexander.rickandmorty.presentation.screen.favorites.model.FavoritesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    observeFavoritesUseCase: ObserveFavoritesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FavoritesViewState())
    val state: StateFlow<FavoritesViewState> = _state.asStateFlow()

    init {
        observeFavoritesUseCase()
            .onStart {
                _state.update { it.copy(isLoading = true, error = null) }
            }
            .catch { e ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Не удалось загрузить избранное",
                    )
                }
            }
            .onEach { favorites ->
                _state.update {
                    it.copy(
                        favorites = favorites,
                        isLoading = false,
                        error = null,
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}