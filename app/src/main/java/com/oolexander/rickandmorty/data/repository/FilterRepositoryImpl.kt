package com.oolexander.rickandmorty.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.oolexander.rickandmorty.domain.model.CharacterFilters
import com.oolexander.rickandmorty.domain.model.CharacterGender
import com.oolexander.rickandmorty.domain.model.CharacterStatus
import com.oolexander.rickandmorty.domain.repository.FilterRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.filtersDataStore by preferencesDataStore(name = "character_filters")

@Singleton
class FilterRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : FilterRepository {
    private object Keys {
        val STATUS = stringPreferencesKey("status_eng")
        val GENDER = stringPreferencesKey("gender_eng")
        val SPECIES = stringPreferencesKey("species")
    }

    override fun observeFilters(): Flow<CharacterFilters> {
        return context.filtersDataStore.data
            .catch { e ->
                if (e is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw e
                }
            }
            .map { prefs ->
                val statusEng = prefs[Keys.STATUS]
                val genderEng = prefs[Keys.GENDER]
                val speciesRaw = prefs[Keys.SPECIES]

                CharacterFilters(
                    status = statusEng?.takeIf { it.isNotBlank() }?.let {
                        CharacterStatus.fromEng(it)
                    },
                    gender = genderEng?.takeIf { it.isNotBlank() }?.let {
                        CharacterGender.fromEng(it)
                    },
                    species = speciesRaw?.takeIf { it.isNotBlank() },
                )
            }
    }

    override suspend fun setFilters(filters: CharacterFilters) {
        context.filtersDataStore.edit { prefs ->
            prefs[Keys.STATUS] = filters.status?.engName ?: ""
            prefs[Keys.GENDER] = filters.gender?.engName ?: ""
            prefs[Keys.SPECIES] = filters.species ?: ""
        }
    }

    override suspend fun clearFilters() {
        context.filtersDataStore.edit { prefs ->
            prefs[Keys.STATUS] = ""
            prefs[Keys.GENDER] = ""
            prefs[Keys.SPECIES] = ""
        }
    }
}