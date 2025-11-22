package com.oolexander.rickandmorty.di

import com.oolexander.rickandmorty.data.models.data.CharacterRepository
import com.oolexander.rickandmorty.data.models.data.MockData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindCharacterRepository(
        mockData: MockData
    ): CharacterRepository
}