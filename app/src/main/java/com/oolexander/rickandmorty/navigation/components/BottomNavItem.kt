package com.oolexander.rickandmorty.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.oolexander.rickandmorty.navigation.Routes

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    companion object {
        val Characters = BottomNavItem(
            route = Routes.CHARACTERS_LIST,
            title = "Characters",
            icon = Icons.Default.List,
        )

        val Favorites = BottomNavItem(
            route = Routes.FAVORITES_LIST,
            title = "Favorites",
            icon = Icons.Default.Favorite,
        )

        val Profile = BottomNavItem(
            route = Routes.PROFILE,
            title = "Profile",
            icon = Icons.Default.AccountCircle,
        )
    }
}