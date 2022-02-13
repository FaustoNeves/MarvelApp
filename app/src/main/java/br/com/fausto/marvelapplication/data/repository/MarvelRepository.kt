package br.com.fausto.marvelapplication.data.repository

import br.com.fausto.marvelapplication.data.remote.helper.NetworkResponse
import br.com.fausto.marvelapplication.data.remote.responses.characters.CharactersResponse
import br.com.fausto.marvelapplication.data.remote.responses.error.ErrorResponse
import br.com.fausto.marvelapplication.data.remote.retrofit.MarvelRestAPI
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val marvelRestAPI: MarvelRestAPI,
) :
    IMarvelRepository {

    override suspend fun fetchCharacters(searchText: String): NetworkResponse<CharactersResponse, ErrorResponse> {
        return marvelRestAPI.fetchCharacters(searchText = searchText)
    }
}