package com.oolexander.rickandmorty.domain.usecase

import com.oolexander.rickandmorty.data.models.data.UserInfo
import com.oolexander.rickandmorty.domain.repository.ProfileRepository
import com.oolexander.rickandmorty.platform.notification.AlarmScheduler
import javax.inject.Inject

class UpdateUserInfoUseCase @Inject constructor(
    private val repo: ProfileRepository,
    private val alarmScheduler: AlarmScheduler,
) {
    suspend operator fun invoke(userInfo: UserInfo) {
        repo.updateUserInfo(userInfo)

        if (userInfo.favoriteTime.isNotBlank()) { alarmScheduler.schedule(userInfo) }
    }
}