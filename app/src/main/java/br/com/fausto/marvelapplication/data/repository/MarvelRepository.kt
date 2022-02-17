package br.com.fausto.marvelapplication.data.repository

import br.com.fausto.marvelapplication.data.remote.helper.NetworkResponse
import br.com.fausto.marvelapplication.data.remote.responses.category.CategoryResponse
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

    override suspend fun fetchComicsById(id: Int): NetworkResponse<CategoryResponse, ErrorResponse> {
        return marvelRestAPI.fetchComicsById(id = id)
    }

    override suspend fun fetchSeriesById(id: Int): NetworkResponse<CategoryResponse, ErrorResponse> {
        return marvelRestAPI.fetchSeriesById(id)
    }
}