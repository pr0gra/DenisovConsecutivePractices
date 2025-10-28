package com.oolexander.rickandmorty.presentation.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolexander.rickandmorty.domain.usecase.GetCharactersUseCase
import com.oolexander.rickandmorty.presentation.screen.list.model.CharacterListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        CharacterListUiState(isLoading = false)
    )
    val uiState: StateFlow<CharacterListUiState> = _uiState.asStateFlow()

    init {
        loadPage(page = 1, append = false, force = true)
    }

    private fun loadPage(
        page: Int,
        append: Boolean,
        force: Boolean = false
    ) {
        val snapshot = _uiState.value

        if (!force) {
            if (append) {
                if (snapshot.isLoadingNextPage || snapshot.endReached || snapshot.isLoading) {
                    return
                }
            } else {
                if (snapshot.isLoading) {
                    return
                }
            }
        }

        _uiState.update {
            it.copy(
                isLoading = !append,
                isLoadingNextPage = append,
                error = null
            )
        }

        viewModelScope.launch {
            try {
                val current = _uiState.value

                val characters = getCharactersUseCase(
                    page = page,
                    name = current.searchText,
                    status = current.statusFilter?.engName,
                    gender = current.genderFilter?.engName,
                    species = current.speciesFilter,
                )

                val reachedEnd = characters.isEmpty()

                _uiState.update { state ->
                    state.copy(
                        characters = if (append) state.characters + characters else characters,
                        currentPage = page,
                        isLoading = false,
                        isLoadingNextPage = false,
                        endReached = reachedEnd,
                        error = null,
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isLoadingNextPage = false,
                        error = e.message ?: "Loading error"
                    )
                }
            }
        }
    }

    fun loadNextPage() {
        val state = _uiState.value

        if (state.endReached) return
        if (state.isLoading || state.isLoadingNextPage) return

        val nextPage = state.currentPage + 1
        loadPage(page = nextPage, append = true, force = false)
    }

    fun searchByName(query: String) {
        _uiState.update {
            it.copy(
                searchText = query.ifBlank { null },
                currentPage = 1,
                endReached = false,
                error = null,
                isLoading = false,
                isLoadingNextPage = false,
            )
        }

        loadPage(page = 1, append = false, force = true)
    }
}