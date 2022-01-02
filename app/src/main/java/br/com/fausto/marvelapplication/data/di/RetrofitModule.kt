package br.com.fausto.marvelapplication.data.di

import br.com.fausto.marvelapplication.data.datasource.remote.retrofit.MarvelRestAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun providesMarvelAPI(): MarvelRestAPI =
        Retrofit.Builder()
            .baseUrl("http://gateway.marvel.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelRestAPI::class.java)
}