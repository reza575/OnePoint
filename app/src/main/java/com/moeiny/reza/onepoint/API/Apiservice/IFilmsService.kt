package com.moeiny.reza.onepoint.API.Apiservice

import com.moeiny.reza.onepoint.model.Character
import com.moeiny.reza.onepoint.model.Films
import com.moeiny.reza.onepoint.utils.FilmsCallback


interface IFilmsService {

    fun getFilmsInfo(block: FilmsCallback<Films, Throwable>)

    fun getCharactersInfo(block: FilmsCallback<Character, Throwable>)

}