package com.oolexander.rickandmorty.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.scene.rememberSceneSetupNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.oolexander.rickandmorty.navigation.components.BottomNavItem
import com.oolexander.rickandmorty.navigation.components.BottomNavigationBar
import com.oolexander.rickandmorty.navigation.components.TopBar
import com.oolexander.rickandmorty.ui.screen.details.CharacterDetailScreen
import com.oolexander.rickandmorty.ui.screen.list.CharacterListScreen

@Composable
fun NavGraph() {
    val backStack = remember { mutableStateListOf<Any>(Characters) }

    Scaffold(
        topBar = {
            val currentRoute = backStack.lastOrNull()
            val showBack = currentRoute != Characters || backStack.size > 1
            TopBar(
                title = when (currentRoute) {
                    Characters -> "Characters"
                    is CharacterDetail -> "Character Details"
                    else -> "Unknown"
                },
                showBackButton = showBack,
                onBackClick = { backStack.removeLastOrNull() }
            )
        },
        bottomBar = {
            val currentRoute = backStack.lastOrNull()
            if (currentRoute == Characters) {
                BottomNavigationBar(
                    items = listOf(BottomNavItem.Characters),
                    selectedItem = BottomNavItem.Characters,
                    onItemSelected = { }
                )
            }
        },
        containerColor = Color(0xFF0F1123)
    ) { innerPadding ->
        NavDisplay(
            entryDecorators = listOf(
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = { key ->
                when (key) {
                    Characters -> NavEntry(key) {
                        CharacterListScreen(
                            onCharacterClick = { characterId ->
                                backStack.add(CharacterDetail(characterId))
                            },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                    is CharacterDetail -> NavEntry(key) {
                        CharacterDetailScreen(
                            characterId = key.id,
                            onBackClick = {
                                backStack.removeLastOrNull()
                            },
                            modifier = Modifier
                        )
                    }

                    else -> NavEntry(Unit) { Text("Unknown route") }
                }
            }
        )
    }
}