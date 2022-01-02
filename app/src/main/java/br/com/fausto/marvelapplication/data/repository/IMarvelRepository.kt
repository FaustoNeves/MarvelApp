package br.com.fausto.marvelapplication.data.repository

import br.com.fausto.marvelapplication.data.datasource.responses.hero.MarvelHero

interface IMarvelRepository {
    suspend fun fetchHeroes(): MarvelHero
}