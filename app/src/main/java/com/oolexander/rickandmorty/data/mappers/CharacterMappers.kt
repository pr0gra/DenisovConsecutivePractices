package com.oolexander.rickandmorty.data.mappers

import com.oolexander.rickandmorty.data.models.dto.CharacterDto
import com.oolexander.rickandmorty.data.models.dto.CharacterGenderDto
import com.oolexander.rickandmorty.data.models.dto.CharacterStatusDto
import com.oolexander.rickandmorty.data.models.dto.LocationDto
import com.oolexander.rickandmorty.data.models.entity.EmbeddedLocationEntity
import com.oolexander.rickandmorty.data.models.entity.FavoriteCharacterEntity
import com.oolexander.rickandmorty.domain.model.Character
import com.oolexander.rickandmorty.domain.model.CharacterDetails
import com.oolexander.rickandmorty.domain.model.CharacterGender
import com.oolexander.rickandmorty.domain.model.CharacterStatus
import com.oolexander.rickandmorty.domain.model.Location

fun CharacterDto.toReducedDomain(): Character = Character(
    id = id,
    name = name,
    status = status.toDomain(),
    species = species,
    gender = gender.toDomain(),
    location = location.toDomain(),
    image = image,
)

fun CharacterDto.toDomain(): CharacterDetails = CharacterDetails(
    id = id,
    name = name,
    status = status.toDomain(),
    species = species,
    type = type,
    gender = gender.toDomain(),
    origin = origin.toDomain(),
    location = location.toDomain(),
    image = image,
    episode = episode,
    created = created,
)

fun CharacterStatusDto.toDomain() = CharacterStatus.valueOf(this.name)

fun CharacterGenderDto.toDomain() = CharacterGender.valueOf(this.name)

fun LocationDto.toDomain(): Location = Location(
    name = name,
    url = url,
)

fun CharacterDetails.toEntity(): FavoriteCharacterEntity {
    return FavoriteCharacterEntity(
        id = id,
        name = name,
        status = status.engName,
        species = species,
        type = type,
        gender = gender.engName,
        origin = EmbeddedLocationEntity(
            name = origin.name,
            url = origin.url,
        ),
        location = EmbeddedLocationEntity(
            name = location.name,
            url = location.url,
        ),
        image = image,
        episodes = episode,
        created = created,
    )
}

fun FavoriteCharacterEntity.toReducedDomain(): Character = Character(
    id = id,
    name = name,
    status = CharacterStatus.fromEng(status),
    species = species,
    gender = CharacterGender.fromEng(gender),
    location = Location(
        name = location.name,
        url = location.url,
    ),
    image = image,
)

fun FavoriteCharacterEntity.toDomain(): CharacterDetails {
    return CharacterDetails(
        id = id,
        name = name,
        status = CharacterStatus.fromEng(status),
        species = species,
        type = type,
        gender = CharacterGender.fromEng(gender),
        origin = Location(
            name = origin.name,
            url = origin.url,
        ),
        location = Location(
            name = location.name,
            url = location.url,
        ),
        image = image,
        episode = episodes,
        created = created,
    )
}