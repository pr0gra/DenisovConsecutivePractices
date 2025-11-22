package com.oolexander.rickandmorty.domain.usecase

import com.oolexander.rickandmorty.data.models.data.UserInfo
import com.oolexander.rickandmorty.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveUserInfoUseCase @Inject constructor(private val repo: ProfileRepository) {
    operator fun invoke(): Flow<UserInfo> = repo.observeUserInfo()
}