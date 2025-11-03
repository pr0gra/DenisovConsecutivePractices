package com.oolexander.rickandmorty.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    showFilters: Boolean,
    showBackButton: Boolean,
    hasActiveFilters: Boolean,
    onBackClick: () -> Unit,
    onFiltersClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF1A1C29),
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White
        ),
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
        },
        actions = {
            if (showFilters) {
                IconButton(onClick = onFiltersClick) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Filters",
                        modifier = Modifier.size(30.dp),
                    )
                }

                if (hasActiveFilters) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .offset(x = (-18).dp, y = (-10).dp)
                            .clip(CircleShape)
                            .background(Color(0xFF97CE4C)),
                    )
                }
            }
        },
    )
}