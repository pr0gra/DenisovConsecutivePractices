package com.oolexander.rickandmorty.presentation.screen.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.oolexander.rickandmorty.presentation.screen.favorites.model.FavoritesViewState
import com.oolexander.rickandmorty.presentation.screen.list.view.EmptyState
import com.oolexander.rickandmorty.presentation.screen.list.view.ErrorState
import com.oolexander.rickandmorty.presentation.ui_kit.view.CharacterItem

@Composable
fun FavoritesScreen(
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

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
            ),
    ) {
        FavoritesContent(
            state = state,
            onCharacterClick = onCharacterClick,
        )
    }
}

@Composable
private fun FavoritesContent(
    state: FavoritesViewState,
    onCharacterClick: (Int) -> Unit,
) {
    when {
        state.showLoading -> {
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

        state.showError -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                ErrorState(message = state.error ?: "Ошибка")
            }
        }

        state.showEmptyState -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                EmptyState()
            }
        }

        state.showContent -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
                verticalArrangement = spacedBy(16.dp),
            ) {
                items(
                    items = state.favorites,
                    key = { it.id },
                ) { character ->
                    CharacterItem(
                        character = character,
                        isFavorite = true,
                        onClick = { onCharacterClick(character.id) },
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                    )
                }
            }
        }
    }
}