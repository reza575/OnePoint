package com.moeiny.reza.onepoint.API.Apirepo


import com.moeiny.reza.onepoint.model.Character
import com.moeiny.reza.onepoint.model.Films
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.http.*

interface FilmsApiService {

    @GET
    fun getFilmsInfo(@Url url: String): Flowable<Films>

    @GET
    fun getCharactersInfo(@Url url: String): Flowable<Character>


    companion object Factory {
        fun create(retrofit: Retrofit): FilmsApiService {
            return retrofit.create(FilmsApiService::class.java)
        }
    }
}