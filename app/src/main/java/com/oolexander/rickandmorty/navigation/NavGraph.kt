package com.oolexander.rickandmorty.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.oolexander.rickandmorty.navigation.components.BottomNavItem
import com.oolexander.rickandmorty.navigation.components.BottomNavigationBar
import com.oolexander.rickandmorty.navigation.components.FiltersBadgeViewModel
import com.oolexander.rickandmorty.navigation.components.TopBar
import com.oolexander.rickandmorty.presentation.screen.details.CharacterDetailScreen
import com.oolexander.rickandmorty.presentation.screen.favorites.FavoritesScreen
import com.oolexander.rickandmorty.presentation.screen.filters.FiltersScreen
import com.oolexander.rickandmorty.presentation.screen.list.CharacterListScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route

    val filtersBadgeViewModel: FiltersBadgeViewModel = hiltViewModel()
    val hasActiveFilters by filtersBadgeViewModel.hasActiveFilters.collectAsState()

    val bottomItems = listOf(BottomNavItem.Characters, BottomNavItem.Favorites)

    val showBottomBar = currentDestination?.hierarchy?.any { dest ->
        dest.route == Routes.CHARACTERS_LIST || dest.route == Routes.FAVORITES_LIST
    } == true

    val showBackButton = when (currentRoute) {
        Routes.CHARACTERS_LIST,
        Routes.FAVORITES_LIST -> false

        else -> true
    }

    val showFilters = when (currentRoute) {
        Routes.CHARACTERS_LIST,
        Routes.FAVORITES_LIST -> true

        else -> false
    }

    val topBarTitle = when {
        currentRoute == Routes.CHARACTERS_LIST -> "Characters"
        currentRoute == Routes.FAVORITES_LIST -> "Favorites"
        currentRoute?.startsWith("details") == true -> "Character Details"
        currentRoute == Routes.FILTERS -> "Filters"
        else -> "Unknown"
    }

    Scaffold(
        topBar = {
            TopBar(
                title = topBarTitle,
                showFilters = showFilters,
                showBackButton = showBackButton,
                hasActiveFilters = hasActiveFilters,
                onBackClick = { navController.popBackStack() },
                onFiltersClick = { navController.navigate(Routes.FILTERS) },
            )
        },
        bottomBar = {
            if (showBottomBar) {
                val selectedItem = bottomItems.find { item ->
                    currentDestination.hierarchy.any { it.route == item.route }
                } ?: BottomNavItem.Characters

                BottomNavigationBar(
                    items = bottomItems,
                    selectedItem = selectedItem,
                    onItemSelected = { item ->
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        },
        containerColor = Color(0xFF0F1123),
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.CHARACTERS_LIST,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Routes.CHARACTERS_LIST) {
                CharacterListScreen(
                    onCharacterClick = { characterId ->
                        navController.navigate("details/$characterId")
                    },
                )
            }

            composable(Routes.FAVORITES_LIST) {
                FavoritesScreen(
                    onCharacterClick = { characterId ->
                        navController.navigate("details/$characterId")
                    },
                )
            }

            composable(Routes.FILTERS) {
                FiltersScreen(onDone = { navController.popBackStack() })
            }

            composable(Routes.CHARACTER_DETAILS) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
                CharacterDetailScreen(characterId = id ?: -1)
            }
        }
    }
}