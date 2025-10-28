package com.oolexander.rickandmorty.presentation.screen.list.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oolexander.rickandmorty.domain.model.Character
import com.oolexander.rickandmorty.presentation.screen.list.utils.getStatusColor

@Composable
internal fun CharacterItem(
    character: Character,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
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
                    contentScale = ContentScale.Crop,
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