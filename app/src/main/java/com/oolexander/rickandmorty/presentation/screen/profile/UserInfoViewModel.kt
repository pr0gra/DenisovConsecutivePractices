package com.oolexander.rickandmorty.presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oolexander.rickandmorty.data.models.data.UserInfo
import com.oolexander.rickandmorty.domain.usecase.ObserveUserInfoUseCase
import com.oolexander.rickandmorty.domain.usecase.UpdateUserInfoUseCase
import com.oolexander.rickandmorty.presentation.screen.profile.model.UserInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val observeUserInfoUseCase: ObserveUserInfoUseCase,
    private val updateUserInfoUseCase: UpdateUserInfoUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(UserInfoState())
    val state: StateFlow<UserInfoState> = _state

    init {
        loadUserInfo()
    }

    private fun loadUserInfo() {
        viewModelScope.launch {
            observeUserInfoUseCase().collect { profile ->
                _state.value = UserInfoState(
                    username = profile.name,
                    post = profile.post,
                    photoUri = profile.photoUri,
                    resumeUrl = profile.resumeUrl,
                    favoriteTime = profile.favoriteTime,
                )
            }
        }
    }

    fun onUsernameChange(username: String) = _state.update { it.copy(username = username) }
    fun onPostChange(post: String) = _state.update { it.copy(post = post) }
    fun onPhotoUriChange(photoUri: String) = _state.update { it.copy(photoUri = photoUri) }
    fun onResumeUrlChange(resumeUrl: String) = _state.update { it.copy(resumeUrl = resumeUrl) }

    fun onFavoriteTimeChange(time: String) {
        _state.update { it.copy(favoriteTime = time, timeError = null) }
    }

    private fun validateTime(text: String): Boolean {
        if (text.length != 5) return false
        if (text[2] != ':') return false

        val hour = text.take(2).toIntOrNull() ?: return false
        val minute = text.substring(3, 5).toIntOrNull() ?: return false

        return hour in 0..23 && minute in 0..59
    }

    fun updateUserInfo(): Boolean {
        val state = _state.value

        if (state.favoriteTime.isNotBlank()) {
            if (!validateTime(state.favoriteTime)) {
                _state.update { it.copy(timeError = "Некорректный формат времени") }
                return false
            }
        }

        _state.update { it.copy(timeError = null) }

        viewModelScope.launch {
            val userInfo = UserInfo(
                name = state.username,
                post = state.post,
                photoUri = state.photoUri,
                resumeUrl = state.resumeUrl,
                favoriteTime = state.favoriteTime.ifBlank { "" },
            )

            updateUserInfoUseCase(userInfo)
        }

        return true
    }
}