package com.oolexander.rickandmorty.data.datasource.cache

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.oolexander.rickandmorty.data.models.data.UserInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.profileDataStore by preferencesDataStore("user_profile")

@Singleton
class UserInfoPreferencesDataStore @Inject constructor(
    @param:ApplicationContext private val context: Context,
) {

    companion object {
        private val KEY_NAME = stringPreferencesKey("name")
        private val KEY_POST = stringPreferencesKey("post")
        private val KEY_PHOTO_URI = stringPreferencesKey("photo_uri")
        private val KEY_RESUME_URL = stringPreferencesKey("resume_url")
    }

    val profileFlow: Flow<UserInfo> = context.profileDataStore.data.map { prefs ->
        UserInfo(
            name = prefs[KEY_NAME] ?: "",
            post = prefs[KEY_POST] ?: "",
            photoUri = prefs[KEY_PHOTO_URI] ?: "",
            resumeUrl = prefs[KEY_RESUME_URL] ?: ""
        )
    }

    suspend fun updateProfile(profile: UserInfo) {
        context.profileDataStore.edit { prefs ->
            prefs[KEY_NAME] = profile.name
            prefs[KEY_POST] = profile.post
            prefs[KEY_PHOTO_URI] = profile.photoUri
            prefs[KEY_RESUME_URL] = profile.resumeUrl
        }
    }
}