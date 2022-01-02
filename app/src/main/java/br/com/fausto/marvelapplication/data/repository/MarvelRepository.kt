package br.com.fausto.marvelapplication.data.repository

import br.com.fausto.marvelapplication.data.datasource.remote.retrofit.MarvelRestAPI
import br.com.fausto.marvelapplication.data.datasource.responses.hero.MarvelHero
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val remoteSource: MarvelRestAPI
) :
    IMarvelRepository {

    override suspend fun fetchHeroes(): MarvelHero {
        return remoteSource.getAllHeroes()
    }
}