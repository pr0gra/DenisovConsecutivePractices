package com.oolexander.rickandmorty.presentation.screen.details.utils

import androidx.compose.ui.graphics.Color
import com.oolexander.rickandmorty.domain.model.CharacterStatus

internal fun getStatusColor(status: CharacterStatus): Color {
    return when (status) {
        CharacterStatus.ALIVE -> Color(0xFF97CE4C)
        CharacterStatus.DEAD -> Color(0xFFE84C5C)
        CharacterStatus.UNKNOWN -> Color(0xFF8A8D9F)
    }
}