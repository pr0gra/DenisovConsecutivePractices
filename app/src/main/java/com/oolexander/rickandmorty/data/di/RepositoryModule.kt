package com.oolexander.rickandmorty.data.di

import com.oolexander.rickandmorty.data.repository.CharacterRepositoryImpl
import com.oolexander.rickandmorty.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindCharacterRepository(impl: CharacterRepositoryImpl): CharacterRepository
}