package com.moeiny.reza.onepoint.repository.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    fun getClient():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://swapi.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}