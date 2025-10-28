package com.oolexander.rickandmorty.presentation.screen.details.utils

internal fun formatDate(dateString: String): String {
    return try {
        dateString.substring(0, 10)
    } catch (e: Exception) {
        dateString
    }
}