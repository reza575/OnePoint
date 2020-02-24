package com.moeiny.reza.onepoint.repository

import android.app.Application
import android.os.AsyncTask
import com.moeiny.reza.deloittest.repository.database.entitiy.CharacterEntity
import com.moeiny.reza.deloittest.repository.database.entitiy.FilmEntity
import com.moeiny.reza.onepoint.database.AppDatabase
import com.moeiny.reza.onepoint.database.dao.CharacterDao
import com.moeiny.reza.onepoint.database.dao.FilmDao

class FilmsRepository(application: Application){

    private  var filmDao: FilmDao
    private  var characterDao: CharacterDao


    private  var allFilmsData:List<FilmEntity>
    private  var allCharactersData:List<CharacterEntity>


    init {
        val db: AppDatabase = AppDatabase.getInstance(
            application.applicationContext )!!

        filmDao = db.FilmDao()
        characterDao = db.CharacterDao()

        allFilmsData = filmDao.getAll()
        allCharactersData = characterDao.getAll()

    }

    ////////////////////////////////Films////////////////////////////

    fun insertFilm(filmEntity: FilmEntity){
        FilmInsert(filmDao).execute(filmEntity)
    }

    fun updateFilm(filmEntity: FilmEntity){
        FilmUpdate(filmDao).execute(filmEntity)
    }

    fun deleteFilm(filmEntity: FilmEntity){
        FilmDelete(filmDao).execute(filmEntity)
    }

    fun deleteAllFilms(){
        filmDao.deleteAll()
    }

    fun getAllFilms():List<FilmEntity>{
        return allFilmsData
    }

    fun getFilmbyEpisodeId(id:Int):FilmEntity{
        return filmDao.findByid(id)
    }



    private class FilmInsert(filmDao: FilmDao): AsyncTask<FilmEntity, Void, Void>(){

        private var filmDao: FilmDao
        init{
            this.filmDao=filmDao
        }

        override fun doInBackground(vararg p0: FilmEntity): Void? {
            filmDao.insert(p0[0])
            return null
        }

    }

    private class FilmUpdate(filmDao: FilmDao): AsyncTask<FilmEntity, Void, Void>(){

        private var filmDao:FilmDao
        init{
            this.filmDao=filmDao
        }

        override fun doInBackground(vararg p0: FilmEntity): Void? {
            filmDao.update(p0[0])
            return null
        }
    }

    private class FilmDelete(filmDao: FilmDao): AsyncTask<FilmEntity, Void, Void>(){

        private var filmDao:FilmDao
        init{
            this.filmDao=filmDao
        }

        override fun doInBackground(vararg p0: FilmEntity): Void? {
            filmDao.delete(p0[0])
            return null
        }
    }

    ////////////////////////////////Character////////////////////////////

    fun insertCharacter(characterEntity: CharacterEntity){
        CharacterInsert(characterDao).execute(characterEntity)
    }

    fun updateCharacter(characterEntity: CharacterEntity){
        CharacterUpdate(characterDao).execute(characterEntity)
    }

    fun deleteCharacter(characterEntity: CharacterEntity){
        CharacterDelete(characterDao).execute(characterEntity)
    }

    fun deleteAllCharacters(){
        characterDao.deleteAll()
    }

    fun getAllCharacters():List<CharacterEntity>{
        return allCharactersData
    }

    fun getCharacterbyId(id:Int):CharacterEntity{
        return characterDao.findByid(id)
    }



    private class CharacterInsert(characterDao: CharacterDao): AsyncTask<CharacterEntity, Void, Void>(){

        private var characterDao: CharacterDao
        init{
            this.characterDao=characterDao
        }

        override fun doInBackground(vararg p0: CharacterEntity): Void? {
            characterDao.insert(p0[0])
            return null
        }

    }

    private class CharacterUpdate(characterDao: CharacterDao): AsyncTask<CharacterEntity, Void, Void>(){

        private var characterDao:CharacterDao
        init{
            this.characterDao=characterDao
        }

        override fun doInBackground(vararg p0: CharacterEntity): Void? {
            characterDao.update(p0[0])
            return null
        }
    }

    private class CharacterDelete(characterDao: CharacterDao): AsyncTask<CharacterEntity, Void, Void>(){

        private var characterDao:CharacterDao
        init{
            this.characterDao=characterDao
        }

        override fun doInBackground(vararg p0: CharacterEntity): Void? {
            characterDao.delete(p0[0])
            return null
        }
    }
}




