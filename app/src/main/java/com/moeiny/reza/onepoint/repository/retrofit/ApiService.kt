package com.moeiny.reza.onepoint.repository.retrofit

import com.moeiny.reza.onepoint.repository.model.Character
import com.moeiny.reza.onepoint.repository.model.Films
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("api/films")
    fun getFilmsInfo():Call<Films>

    //   @FormUrlEncoded
    @GET
    fun getCharactersInfo(@Url url:String ):Call<Character>
}