package com.oolexander.rickandmorty.data.datasource.remote

import com.oolexander.rickandmorty.data.models.dto.CharacterDto
import com.oolexander.rickandmorty.data.models.dto.CharactersPageDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("gender") gender: String? = null,
    ): CharactersPageDto

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int,
    ): CharacterDto
}