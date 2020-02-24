package com.moeiny.reza.onepoint.API.Apirepo

import com.moeiny.reza.onepoint.model.Character
import com.moeiny.reza.onepoint.model.Films
import com.moeiny.reza.onepoint.utils.API
import io.reactivex.Flowable

class FilmsRepository(private val filmsApiService: FilmsApiService) {

    fun getFilmsInfo(): Flowable<Films> {
        return filmsApiService.getFilmsInfo(API.GET_FILM_URL.value)
    }

    fun getCharactersInfo(id: Int): Flowable<Character> {
        return filmsApiService.getCharactersInfo(API.GET_CHARACTER_URL.value + id.toString())
    }

}
