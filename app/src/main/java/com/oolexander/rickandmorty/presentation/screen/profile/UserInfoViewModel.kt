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
                    resumeUrl = profile.resumeUrl
                )
            }
        }
    }

    fun onUsernameChange(username: String) = _state.update { it.copy(username = username) }
    fun onPostChange(post: String) = _state.update { it.copy(post = post) }
    fun onPhotoUriChange(photoUri: String) = _state.update { it.copy(photoUri = photoUri) }
    fun onResumeUrlChange(resumeUrl: String) = _state.update { it.copy(resumeUrl = resumeUrl) }

    fun updateUserInfo() {
        viewModelScope.launch {
            val userInfo = UserInfo(
                name = _state.value.username,
                post = _state.value.post,
                photoUri = _state.value.photoUri,
                resumeUrl = _state.value.resumeUrl
            )

            updateUserInfoUseCase(userInfo)
        }
    }
}