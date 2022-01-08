package br.com.fausto.marvelapplication.data.repository

import br.com.fausto.marvelapplication.data.remote.helper.NetworkResponse
import br.com.fausto.marvelapplication.data.remote.responses.all_heroes.AllMarvelHeroesResponse
import br.com.fausto.marvelapplication.data.remote.responses.error.ErrorResponse
import br.com.fausto.marvelapplication.data.remote.retrofit.MarvelRestAPI
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val marvelRestAPI: MarvelRestAPI,
) :
    IMarvelRepository {

    override suspend fun fetchHeroes(firstChar: String): NetworkResponse<AllMarvelHeroesResponse, ErrorResponse> {
        return marvelRestAPI.getAllHeroes(firstChar = firstChar)
    }
}