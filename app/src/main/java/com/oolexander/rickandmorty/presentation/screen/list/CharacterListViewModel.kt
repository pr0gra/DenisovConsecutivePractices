package com.oolexander.rickandmorty.presentation.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolexander.rickandmorty.domain.model.CharacterFilters
import com.oolexander.rickandmorty.domain.usecase.GetCharactersUseCase
import com.oolexander.rickandmorty.domain.usecase.ObserveFavoritesUseCase
import com.oolexander.rickandmorty.domain.usecase.ObserveFiltersUseCase
import com.oolexander.rickandmorty.presentation.screen.list.model.CharacterListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val observeFavoritesUseCase: ObserveFavoritesUseCase,
    private val observeFiltersUseCase: ObserveFiltersUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterListUiState(isLoading = false))
    val uiState: StateFlow<CharacterListUiState> = _uiState.asStateFlow()

    init {
        observeFavorites()
        observeFilters()

        loadPage(page = 1, append = false, force = true)
    }

    private fun observeFavorites() {
        observeFavoritesUseCase()
            .onEach { favoritesList ->
                val favIds = favoritesList.map { it.id }.toSet()
                _uiState.update { state -> state.copy(favoriteIds = favIds) }
            }
            .launchIn(viewModelScope)
    }

    private fun observeFilters() {
        observeFiltersUseCase()
            .onEach { filters: CharacterFilters ->
                applyFiltersFromDataStore(filters)
            }
            .launchIn(viewModelScope)
    }

    private fun applyFiltersFromDataStore(filters: CharacterFilters) {
        _uiState.update {
            it.copy(
                statusFilter = filters.status,
                genderFilter = filters.gender,
                speciesFilter = filters.species,
                currentPage = 1,
                endReached = false,
                error = null,
                isLoading = false,
                isLoadingNextPage = false,
            )
        }

        reloadFromFilters()
    }

    private fun reloadFromFilters() {
        loadPage(
            page = 1,
            append = false,
            force = true,
        )
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

    private fun loadPage(
        page: Int,
        append: Boolean,
        force: Boolean = false,
    ) {
        val snapshot = _uiState.value

        if (!force && shouldBlockLoad(snapshot, append)) { return }
        setLoadingState(isAppend = append)

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
                        error = e.message ?: "Loading error",
                    )
                }
            }
        }
    }

    private fun shouldBlockLoad(
        state: CharacterListUiState,
        append: Boolean,
    ): Boolean = if (append) {
        state.isLoadingNextPage || state.endReached || state.isLoading
    } else {
        state.isLoading
    }

    private fun setLoadingState(isAppend: Boolean) {
        _uiState.update { state ->
            state.copy(
                isLoading = !isAppend,
                isLoadingNextPage = isAppend,
                error = null,
            )
        }
    }
}