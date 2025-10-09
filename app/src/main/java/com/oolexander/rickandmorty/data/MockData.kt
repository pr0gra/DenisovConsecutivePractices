package com.oolexander.rickandmorty.data

import com.oolexander.rickandmorty.model.Character
import com.oolexander.rickandmorty.model.CharacterGender
import com.oolexander.rickandmorty.model.CharacterStatus
import com.oolexander.rickandmorty.model.Location
import javax.inject.Inject

interface CharacterRepository {
    val mockCharacters: List<Character>
}

class MockData @Inject constructor() : CharacterRepository {
    override val mockCharacters = listOf(
        Character(
            id = 1,
            name = "Rick Sanchez",
            status = CharacterStatus.ALIVE,
            species = "Human",
            type = "",
            gender = CharacterGender.MALE,
            origin = Location("Earth (C-137)", "https://rickandmortyapi.com/api/location/1"),
            location = Location(
                "Earth (Replacement Dimension)",
                "https://rickandmortyapi.com/api/location/20"
            ),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf(
                "https://rickandmortyapi.com/api/episode/1",
                "https://rickandmortyapi.com/api/episode/2"
            ),
            url = "https://rickandmortyapi.com/api/character/1",
            created = "2017-11-04T18:48:46.250Z"
        ),
        Character(
            id = 2,
            name = "Morty Smith",
            status = CharacterStatus.ALIVE,
            species = "Human",
            type = "",
            gender = CharacterGender.MALE,
            origin = Location("Earth (C-137)", "https://rickandmortyapi.com/api/location/1"),
            location = Location(
                "Earth (Replacement Dimension)",
                "https://rickandmortyapi.com/api/location/20"
            ),
            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            episode = listOf(
                "https://rickandmortyapi.com/api/episode/1",
                "https://rickandmortyapi.com/api/episode/2"
            ),
            url = "https://rickandmortyapi.com/api/character/2",
            created = "2017-11-04T18:48:56.250Z"
        ),
        Character(
            id = 3,
            name = "Beth Smith",
            status = CharacterStatus.ALIVE,
            species = "Human",
            type = "",
            gender = CharacterGender.FEMALE,
            origin = Location("Earth (C-137)", ""),
            location = Location("Earth (Replacement Dimension)", ""),
            image = "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/3",
            created = "2017-11-04T18:49:00.000Z"
        ),
        Character(
            id = 4,
            name = "Summer Smith",
            status = CharacterStatus.ALIVE,
            species = "Human",
            type = "",
            gender = CharacterGender.FEMALE,
            origin = Location("Earth (C-137)", ""),
            location = Location("Earth (Replacement Dimension)", ""),
            image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/4",
            created = "2017-11-04T18:49:10.000Z"
        ),
        Character(
            id = 5,
            name = "Jerry Smith",
            status = CharacterStatus.ALIVE,
            species = "Human",
            type = "",
            gender = CharacterGender.MALE,
            origin = Location("Earth (C-137)", ""),
            location = Location("Earth (Replacement Dimension)", ""),
            image = "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/5",
            created = "2017-11-04T18:49:20.000Z"
        )
    )
}