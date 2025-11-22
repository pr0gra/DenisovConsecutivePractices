package com.oolexander.rickandmorty.domain.repository

import com.oolexander.rickandmorty.data.models.data.UserInfo
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun observeUserInfo(): Flow<UserInfo>
    suspend fun updateUserInfo(userInfo: UserInfo)
}