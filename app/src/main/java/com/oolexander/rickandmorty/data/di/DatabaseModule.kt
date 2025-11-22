package com.oolexander.rickandmorty.data.di

import android.content.Context
import androidx.room.Room
import com.oolexander.rickandmorty.data.datasource.local.FavoriteCharacterDao
import com.oolexander.rickandmorty.data.utils.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "rick_and_morty.db",
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteCharacterDao(db: AppDatabase): FavoriteCharacterDao {
        return db.favoriteCharacterDao()
    }
}