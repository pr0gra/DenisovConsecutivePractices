package com.oolexander.rickandmorty.data.repository

import com.oolexander.rickandmorty.data.datasource.cache.UserInfoPreferencesDataStore
import com.oolexander.rickandmorty.data.models.data.UserInfo
import com.oolexander.rickandmorty.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val dataStore: UserInfoPreferencesDataStore,
) : ProfileRepository {
    override fun observeUserInfo(): Flow<UserInfo> = dataStore.profileFlow
    override suspend fun updateUserInfo(userInfo: UserInfo) {
        dataStore.updateProfile(userInfo)
    }
}