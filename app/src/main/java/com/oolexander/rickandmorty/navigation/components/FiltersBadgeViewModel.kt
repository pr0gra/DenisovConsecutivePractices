package com.oolexander.rickandmorty.navigation.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolexander.rickandmorty.domain.usecase.ObserveFiltersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FiltersBadgeViewModel @Inject constructor(
    observeFiltersUseCase: ObserveFiltersUseCase,
) : ViewModel() {

    val hasActiveFilters: StateFlow<Boolean> =
        observeFiltersUseCase()
            .map { filters ->
                filters.status != null ||
                filters.gender != null ||
                !filters.species.isNullOrBlank()
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = false,
            )
}