package com.oolexander.rickandmorty.ui.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.oolexander.rickandmorty.model.Character
import com.oolexander.rickandmorty.model.CharacterStatus

@Composable
fun CharacterListScreen(
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1A1C29),
                        Color(0xFF0F1123)
                    )
                )
            )
    ) {
        when (val state = uiState) {
            ListUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(48.dp),
                        color = Color(0xFF97CE4C)
                    )
                }
            }

            is ListUiState.Success -> {
                if (state.characters.isEmpty()) {
                    EmptyState()
                } else {
                    CharacterList(
                        characters = state.characters,
                        onCharacterClick = onCharacterClick
                    )
                }
            }

            is ListUiState.Error -> {
                ErrorState(message = state.message)
            }
        }
    }
}

@Composable
private fun CharacterList(
    characters: List<Character>,
    onCharacterClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp, bottom = 16.dp),
        verticalArrangement = spacedBy(12.dp)
    ) {
        items(characters) { character ->
            CharacterItem(
                character = character,
                onClick = { onCharacterClick(character.id) }
            )
        }
    }
}

@Composable
private fun CharacterItem(
    character: Character,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2D3047)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = 2.dp,
                            color = getStatusColor(character.status),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .align(Alignment.TopEnd)
                        .background(
                            color = getStatusColor(character.status),
                            shape = CircleShape
                        )
                        .border(
                            width = 1.5.dp,
                            color = Color(0xFF2D3047),
                            shape = CircleShape
                        )
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = character.status.rusName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = getStatusColor(character.status),
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = " • ",
                        color = Color(0xFF8A8D9F)
                    )

                    Text(
                        text = character.species,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF8A8D9F)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = character.gender.rusName,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF97CE4C)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = character.location.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF8A8D9F),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFF1A1C29),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "#${character.id}",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF97CE4C),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "No characters",
            modifier = Modifier.size(64.dp),
            tint = Color(0xFF8A8D9F)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "НИЧЕГО НЕ НАЙДЕНО",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF8A8D9F),
            textAlign = Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Похоже, в этой вселенной пока нет персонажей",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF8A8D9F),
            textAlign = Center
        )
    }
}

@Composable
private fun ErrorState(
    message: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2D3047)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "WUBBA LUBBA DUB DUB!",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE84C5C),
                    textAlign = Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Что-то пошло не так:",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    textAlign = Center
                )
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF8A8D9F),
                    textAlign = Center
                )
            }
        }
    }
}

private fun getStatusColor(status: CharacterStatus): Color {
    return when (status) {
        CharacterStatus.ALIVE -> Color(0xFF97CE4C)
        CharacterStatus.DEAD -> Color(0xFFE84C5C)
        CharacterStatus.UNKNOWN -> Color(0xFF8A8D9F)
    }
}