package com.oolexander.rickandmorty.data.models.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_characters")
data class FavoriteCharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    @Embedded(prefix = "origin_")
    val origin: EmbeddedLocationEntity,
    @Embedded(prefix = "location_")
    val location: EmbeddedLocationEntity,
    val image: String,
    val episodes: List<String>,
    val created: String
)