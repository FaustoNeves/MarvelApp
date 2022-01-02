package br.com.fausto.marvelapplication.data.di

import br.com.fausto.marvelapplication.data.repository.IMarvelRepository
import br.com.fausto.marvelapplication.data.repository.MarvelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MarvelAppModule {

    @Singleton
    @Binds
    abstract fun provideRepository(
        remoteSource: MarvelRepository
    ): IMarvelRepository
}