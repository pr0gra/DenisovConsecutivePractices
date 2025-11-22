package com.oolexander.rickandmorty.data.utils

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromEpisodeList(list: List<String>): String {
        return list.joinToString(separator = "|")
    }

    @TypeConverter
    fun toEpisodeList(data: String): List<String> {
        if (data.isBlank()) return emptyList()
        return data.split("|")
    }
}