package com.oolexander.rickandmorty.data.mappers

import com.oolexander.rickandmorty.data.models.dto.CharacterDto
import com.oolexander.rickandmorty.data.models.dto.CharacterGenderDto
import com.oolexander.rickandmorty.data.models.dto.CharacterStatusDto
import com.oolexander.rickandmorty.data.models.dto.LocationDto
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