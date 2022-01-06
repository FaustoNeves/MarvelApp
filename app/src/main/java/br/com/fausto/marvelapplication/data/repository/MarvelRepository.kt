package br.com.fausto.marvelapplication.data.repository

import br.com.fausto.marvelapplication.data.datasource.remote.retrofit.MarvelRestAPI
import br.com.fausto.marvelapplication.data.datasource.remote.responses.all_heroes.FetchAllMarvelHeroesResponse
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val remoteSource: MarvelRestAPI
) :
    IMarvelRepository {

    override suspend fun fetchHeroes(firstChar: String): FetchAllMarvelHeroesResponse {
        val response = remoteSource.getAllHeroes(firstChar = firstChar)
        return remoteSource.getAllHeroes(firstChar = firstChar)
    }
}