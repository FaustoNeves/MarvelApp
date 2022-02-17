package br.com.fausto.marvelapplication.data.repository

import br.com.fausto.marvelapplication.data.remote.helper.NetworkResponse
import br.com.fausto.marvelapplication.data.remote.responses.category.CategoryResponse
import br.com.fausto.marvelapplication.data.remote.responses.characters.CharactersResponse
import br.com.fausto.marvelapplication.data.remote.responses.error.ErrorResponse

interface IMarvelRepository {
    suspend fun fetchCharacters(searchText: String): NetworkResponse<CharactersResponse, ErrorResponse>
    suspend fun fetchComicsById(id: Int): NetworkResponse<CategoryResponse, ErrorResponse>
    suspend fun fetchSeriesById(id: Int): NetworkResponse<CategoryResponse, ErrorResponse>
}