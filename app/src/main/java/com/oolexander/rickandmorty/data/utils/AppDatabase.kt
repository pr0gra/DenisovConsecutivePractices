package com.oolexander.rickandmorty.data.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.oolexander.rickandmorty.data.datasource.local.FavoriteCharacterDao
import com.oolexander.rickandmorty.data.models.entity.FavoriteCharacterEntity

@Database(
    entities = [FavoriteCharacterEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCharacterDao(): FavoriteCharacterDao
}