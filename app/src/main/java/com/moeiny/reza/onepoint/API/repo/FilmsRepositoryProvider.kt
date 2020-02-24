package com.moeiny.reza.onepoint.API.repo

import retrofit2.Retrofit

object FilmsRepositoryProvider {

    fun provideFilmRepository(retrofit: Retrofit) : FilmsRepository {
        return FilmsRepository(FilmsApiService.create(retrofit))
    }
}