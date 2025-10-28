package com.oolexander.rickandmorty.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Characters

@Serializable
data class CharacterDetail(val id: Int)