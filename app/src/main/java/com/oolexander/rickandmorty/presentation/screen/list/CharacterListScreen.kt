package com.oolexander.rickandmorty.presentation.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.oolexander.rickandmorty.presentation.screen.list.model.CharacterListUiState
import com.oolexander.rickandmorty.presentation.screen.list.view.CharacterItem
import com.oolexander.rickandmorty.presentation.screen.list.view.EmptyState
import com.oolexander.rickandmorty.presentation.screen.list.view.ErrorState
import com.oolexander.rickandmorty.presentation.screen.list.view.SearchBar

@Composable
fun CharacterListScreen(
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1A1C29),
                        Color(0xFF0F1123),
                    ),
                ),
            )
    ) {
        CharacterListContent(
            state = state,
            onCharacterClick = onCharacterClick,
            onBottomReached = { viewModel.loadNextPage() },
            onSearchSubmit = { query -> viewModel.searchByName(query) }
        )
    }
}

@Composable
private fun CharacterListContent(
    state: CharacterListUiState,
    onCharacterClick: (Int) -> Unit,
    onBottomReached: () -> Unit,
    onSearchSubmit: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        verticalArrangement = spacedBy(16.dp),
    ) {
        item(key = "search_bar") {
            SearchBar(
                initialText = state.searchText.orEmpty(),
                onSearch = onSearchSubmit,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
            )
        }

        when {
            state.showLoading -> {
                item(key = "show_loading") {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(48.dp),
                            color = Color(0xFF97CE4C),
                        )
                    }
                }
            }

            state.showError -> {
                item(key = "show_error") { ErrorState(message = state.error ?: "Ошибка") }
            }

            state.showEmptyState -> {
                item(key = "show_empty_state") { EmptyState() }
            }

            else -> {
                itemsIndexed(
                    items = state.characters,
                    key = { _, item -> item.id }
                ) { index, character ->
                    if (index == state.characters.lastIndex) {
                        onBottomReached()
                    }

                    CharacterItem(
                        character = character,
                        onClick = { onCharacterClick(character.id) },
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )
                }

                if (state.isLoadingNextPage) {
                    item(key = "loading_footer") {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(32.dp),
                                color = Color(0xFF97CE4C),
                            )
                        }
                    }
                }
            }
        }
    }
}
