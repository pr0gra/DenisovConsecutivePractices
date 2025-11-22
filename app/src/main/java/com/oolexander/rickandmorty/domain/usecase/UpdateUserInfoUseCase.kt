package com.oolexander.rickandmorty.domain.usecase

import com.oolexander.rickandmorty.data.models.data.UserInfo
import com.oolexander.rickandmorty.domain.repository.ProfileRepository
import javax.inject.Inject

class UpdateUserInfoUseCase @Inject constructor(private val repo: ProfileRepository) {
    suspend operator fun invoke(userInfo: UserInfo) = repo.updateUserInfo(userInfo)
}