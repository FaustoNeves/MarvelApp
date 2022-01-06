package br.com.fausto.marvelapplication.data.repository

import br.com.fausto.marvelapplication.data.datasource.remote.responses.all_heroes.FetchAllMarvelHeroesResponse

interface IMarvelRepository {
    suspend fun fetchHeroes(firstChar: String): FetchAllMarvelHeroesResponse
}