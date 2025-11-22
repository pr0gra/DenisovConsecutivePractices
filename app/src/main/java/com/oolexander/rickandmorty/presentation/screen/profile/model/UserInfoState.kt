package com.oolexander.rickandmorty.presentation.screen.profile.model

data class UserInfoState(
    val username: String = "",
    val post: String = "",
    val photoUri: String = "",
    val resumeUrl: String = "",
    val favoriteTime: String = "",
    val timeError: String? = null,
)