package com.moeiny.reza.onepoint.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.moeiny.reza.deloittest.repository.database.entitiy.CharacterEntity
import com.moeiny.reza.deloittest.repository.database.entitiy.FilmEntity
import com.moeiny.reza.onepoint.repository.FilmsRepository

class FilmsViewModel(application: Application) : AndroidViewModel(application) {

    private  var filmRepository: FilmsRepository
    private  var allFilmsData:List<FilmEntity>
    private  var allCharactersData:List<CharacterEntity>

    init {
        filmRepository= FilmsRepository(application)
        allFilmsData=filmRepository.getAllFilms()
        allCharactersData=filmRepository.getAllCharacters()
    }

    //////////////// implementing Function to Access Movie Details on Database//////////

    fun insert(filmEntity: FilmEntity){
        filmRepository.insertFilm(filmEntity)
    }

    fun update(filmEntity: FilmEntity){
        filmRepository.updateFilm(filmEntity)
    }

    fun delete(filmEntity: FilmEntity){
        filmRepository.deleteFilm(filmEntity)
    }

    fun deleteAllFilms(){
        filmRepository.deleteAllFilms()
    }

    fun getFilmsByEposodeId(eposodId: Int):FilmEntity{
        return filmRepository.getFilmbyEpisodeId(eposodId)
    }

    fun getAllFilms():List<FilmEntity>{
        return allFilmsData
    }

    //////////////// implementing Function to Access Character Details on Database//////////

fun insert(characterEntity: CharacterEntity){
    filmRepository.insertCharacter(characterEntity)
}

    fun update(characterEntity: CharacterEntity){
        filmRepository.updateCharacter(characterEntity)
    }

    fun delete(characterEntity: CharacterEntity){
        filmRepository.deleteCharacter(characterEntity)
    }

    fun deleteAllCharacters(){
        filmRepository.deleteAllCharacters()
    }

    fun getCharacterById(id: Int):CharacterEntity{
        return filmRepository.getCharacterbyId(id)
    }

    fun getAllCharacters():List<CharacterEntity>{
        return allCharactersData
    }

//////////////Calling API methods//////////////////////////////

    fun getFilmsInfo() {
        filmRepository.getFilmsInfo()
    }

    fun getCharacterInfo(item:String) {
        filmRepository.getCharacterInfo(item)
    }
 }