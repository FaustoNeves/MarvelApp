package br.com.fausto.marvelapplication.data.repository

import br.com.fausto.marvelapplication.data.remote.helper.NetworkResponse
import br.com.fausto.marvelapplication.data.remote.responses.all_heroes.AllMarvelHeroesResponse
import br.com.fausto.marvelapplication.data.remote.responses.error.ErrorResponse

interface IMarvelRepository {
    suspend fun fetchHeroes(searchText: String): NetworkResponse<AllMarvelHeroesResponse, ErrorResponse>
}