package com.oolexander.rickandmorty.data.models.data

import com.oolexander.rickandmorty.data.models.dto.CharacterDto
import com.oolexander.rickandmorty.data.models.dto.CharacterGenderDto
import com.oolexander.rickandmorty.data.models.dto.CharacterStatusDto
import com.oolexander.rickandmorty.data.models.dto.LocationDto
import javax.inject.Inject

interface CharacterRepository {
    val mockCharacters: List<CharacterDto>
}

class MockData @Inject constructor() : CharacterRepository {
    override val mockCharacters = listOf(
        CharacterDto(
            id = 1,
            name = "Rick Sanchez",
            status = CharacterStatusDto.ALIVE,
            species = "Human",
            type = "",
            gender = CharacterGenderDto.MALE,
            origin = LocationDto("Earth (C-137)", "https://rickandmortyapi.com/api/location/1"),
            location = LocationDto(
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
        CharacterDto(
            id = 2,
            name = "Morty Smith",
            status = CharacterStatusDto.ALIVE,
            species = "Human",
            type = "",
            gender = CharacterGenderDto.MALE,
            origin = LocationDto("Earth (C-137)", "https://rickandmortyapi.com/api/location/1"),
            location = LocationDto(
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
        CharacterDto(
            id = 3,
            name = "Beth Smith",
            status = CharacterStatusDto.ALIVE,
            species = "Human",
            type = "",
            gender = CharacterGenderDto.FEMALE,
            origin = LocationDto("Earth (C-137)", ""),
            location = LocationDto("Earth (Replacement Dimension)", ""),
            image = "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/3",
            created = "2017-11-04T18:49:00.000Z"
        ),
        CharacterDto(
            id = 4,
            name = "Summer Smith",
            status = CharacterStatusDto.ALIVE,
            species = "Human",
            type = "",
            gender = CharacterGenderDto.FEMALE,
            origin = LocationDto("Earth (C-137)", ""),
            location = LocationDto("Earth (Replacement Dimension)", ""),
            image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/4",
            created = "2017-11-04T18:49:10.000Z"
        ),
        CharacterDto(
            id = 5,
            name = "Jerry Smith",
            status = CharacterStatusDto.ALIVE,
            species = "Human",
            type = "",
            gender = CharacterGenderDto.MALE,
            origin = LocationDto("Earth (C-137)", ""),
            location = LocationDto("Earth (Replacement Dimension)", ""),
            image = "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            url = "https://rickandmortyapi.com/api/character/5",
            created = "2017-11-04T18:49:20.000Z"
        )
    )
}