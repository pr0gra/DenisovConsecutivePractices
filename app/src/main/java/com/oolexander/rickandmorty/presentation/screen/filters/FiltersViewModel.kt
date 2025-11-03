package com.oolexander.rickandmorty.presentation.screen.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolexander.rickandmorty.domain.model.CharacterFilters
import com.oolexander.rickandmorty.domain.model.CharacterGender
import com.oolexander.rickandmorty.domain.model.CharacterStatus
import com.oolexander.rickandmorty.domain.usecase.ObserveFiltersUseCase
import com.oolexander.rickandmorty.domain.usecase.UpdateFiltersUseCase
import com.oolexander.rickandmorty.presentation.screen.filters.model.FiltersViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(
    private val observeFiltersUseCase: ObserveFiltersUseCase,
    private val updateFiltersUseCase: UpdateFiltersUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FiltersViewState())
    val state: StateFlow<FiltersViewState> = _state.asStateFlow()

    init {
        init()
    }

    private fun init() = viewModelScope.launch {
        try {
            val savedFilters: CharacterFilters = observeFiltersUseCase().first()
            _state.value = FiltersViewState.fromDomain(savedFilters)
        } catch (e: Exception) {
            _state.update {
                it.copy(
                    isLoading = false,
                    error = e.message ?: "Не удалось загрузить фильтры",
                )
            }
        }
    }

    fun onStatusSelected(status: CharacterStatus?) {
        _state.update { it.copy(status = status) }
    }

    fun onGenderSelected(gender: CharacterGender?) {
        _state.update { it.copy(gender = gender) }
    }

    fun onSpeciesChanged(species: String) {
        _state.update { it.copy(species = species) }
    }

    fun onReset() {
        _state.update {
            it.copy(
                status = null,
                gender = null,
                species = "",
                error = null,
            )
        }
    }

    fun saveAndReturnFilters() {
        val filtersToSave = _state.value.toDomain()

        viewModelScope.launch {
            if (
                filtersToSave.status == null &&
                filtersToSave.gender == null &&
                filtersToSave.species.isNullOrBlank()
            ) {
                updateFiltersUseCase.clear()
            } else {
                updateFiltersUseCase.set(filtersToSave)
            }
        }

    }
}