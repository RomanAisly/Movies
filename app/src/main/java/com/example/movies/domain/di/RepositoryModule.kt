package com.example.movies.domain.di

import com.example.movies.data.remote.FilmsRepositoryImpl
import com.example.movies.domain.FilmsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule() {

    @Binds
    @Singleton
    abstract fun bindFilmsRepository(
        filmsRepositoryImpl: FilmsRepositoryImpl
    ): FilmsRepository


}