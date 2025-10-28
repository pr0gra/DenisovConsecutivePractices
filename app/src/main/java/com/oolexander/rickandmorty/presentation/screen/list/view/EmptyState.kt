package com.oolexander.rickandmorty.presentation.screen.list.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp

@Composable
internal fun EmptyState() {
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