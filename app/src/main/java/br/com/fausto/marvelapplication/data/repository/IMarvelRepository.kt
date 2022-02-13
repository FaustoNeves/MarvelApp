package br.com.fausto.marvelapplication.data.repository

import br.com.fausto.marvelapplication.data.remote.helper.NetworkResponse
import br.com.fausto.marvelapplication.data.remote.responses.characters.CharactersResponse
import br.com.fausto.marvelapplication.data.remote.responses.error.ErrorResponse

interface IMarvelRepository {
    suspend fun fetchCharacters(searchText: String): NetworkResponse<CharactersResponse, ErrorResponse>
}