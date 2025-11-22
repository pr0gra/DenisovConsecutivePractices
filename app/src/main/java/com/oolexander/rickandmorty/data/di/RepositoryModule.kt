package com.oolexander.rickandmorty.data.di

import com.oolexander.rickandmorty.data.repository.CharacterRepositoryImpl
import com.oolexander.rickandmorty.data.repository.FavoritesRepositoryImpl
import com.oolexander.rickandmorty.data.repository.FilterRepositoryImpl
import com.oolexander.rickandmorty.data.repository.ProfileRepositoryImpl
import com.oolexander.rickandmorty.domain.repository.CharacterRepository
import com.oolexander.rickandmorty.domain.repository.FavoritesRepository
import com.oolexander.rickandmorty.domain.repository.FilterRepository
import com.oolexander.rickandmorty.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCharacterRepository(impl: CharacterRepositoryImpl): CharacterRepository

    @Binds
    @Singleton
    abstract fun bindFavoritesRepository(impl: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    @Singleton
    abstract fun bindFilterRepository(impl: FilterRepositoryImpl): FilterRepository

    @Binds
    @Singleton
    abstract fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository
}