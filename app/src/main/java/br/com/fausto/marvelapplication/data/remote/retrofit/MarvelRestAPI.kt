package br.com.fausto.marvelapplication.data.remote.retrofit

import br.com.fausto.marvelapplication.BuildConfig
import br.com.fausto.marvelapplication.data.remote.helper.NetworkResponse
import br.com.fausto.marvelapplication.data.remote.responses.characters.CharactersResponse
import br.com.fausto.marvelapplication.data.remote.responses.error.ErrorResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelRestAPI {

    @GET("characters")
    suspend fun fetchCharacters(
        @Query("ts") ts: String = BuildConfig.API_TS,
        @Query("apikey") key: String = BuildConfig.API_KEY,
        @Query("hash") hash: String = BuildConfig.API_HASH,
        @Query("nameStartsWith") searchText: String,
        @Query("limit") limit: Int = 100
    ): NetworkResponse<CharactersResponse, ErrorResponse>
}