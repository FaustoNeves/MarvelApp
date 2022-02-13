package br.com.fausto.marvelapplication.data.dtos

data class MarvelCharacterDTO(
    val id: Int,
    val description: String,
    val name: String,
    val imagePath: String,
    val urlDetail: String,
)