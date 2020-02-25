package com.moeiny.reza.onepoint.repository

import android.app.Application
import android.os.AsyncTask
import android.view.View
import com.moeiny.reza.deloittest.repository.database.entitiy.CharacterEntity
import com.moeiny.reza.deloittest.repository.database.entitiy.FilmEntity
import com.moeiny.reza.onepoint.repository.model.database.AppDatabase
import com.moeiny.reza.onepoint.repository.model.database.dao.CharacterDao
import com.moeiny.reza.onepoint.repository.model.database.dao.FilmDao
import com.moeiny.reza.onepoint.repository.model.Character
import com.moeiny.reza.onepoint.repository.model.Films
import com.moeiny.reza.onepoint.repository.model.Result
import com.moeiny.reza.onepoint.repository.retrofit.ApiClient
import com.moeiny.reza.onepoint.repository.retrofit.ApiService
import com.moeiny.reza.onepoint.view.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    //////////////// implementing Function to Access Movie Details on Database//////////

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

    //////////////// implementing Function to Access Character Details on Database//////////

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

    //////////////Implementing API methods to get data from API and save in database//////////////////////////////

    fun getFilmsInfo(){

        var apiClient= ApiClient()
        var call: Call<Films> =apiClient.getClient().create(ApiService::class.java).getFilmsInfo()

        call.enqueue(object : Callback<Films> {

            override fun onFailure(call: Call<Films>, t: Throwable) {
                //   Toast.makeText(context,t.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Films>, response: Response<Films>) {
                var films:Films?=response!!.body()
                var resultList = ArrayList<Result>()
                var characterList = ArrayList<String>()
                resultList=films!!.results as ArrayList<Result>
                /*
                * In this section :
                * 1 - All the Movie details fetch from Api
                * 2- Create a filmEntity model for each film and save in the Database
                */
                for (i in 0..resultList.size-1) {
                    characterList=resultList[i].characters as ArrayList<String>

                    var film = FilmEntity(resultList[i].episode_id, resultList[i].created,
                        resultList[i].director, resultList[i].edited, resultList[i].opening_crawl,
                        resultList[i].producer, resultList[i].release_date, resultList[i].title,
                        resultList[i].characters.toString(), resultList[i].planets.toString(),
                        resultList[i].species.toString(), resultList[i].starships.toString(),
                        resultList[i].vehicles.toString(), resultList[i].url)

                    if (getFilmbyEpisodeId(resultList[i].episode_id) == null)
                        insertFilm(film)

                    for(j in 0..characterList.size-1){
                        var item=characterList[j]
                        getCharacterInfo(item)   // calling Api method to get Chatacter Info for each character of Movie
                    }
                }

                var view= HomeActivity.getView()
                view.visibility= View.INVISIBLE
                HomeActivity.move()
            }

        })
    }


    fun getCharacterInfo(item:String){

        var apiClient= ApiClient()
        var call: Call<Character> =apiClient.getClient().create(ApiService::class.java).getCharactersInfo(item)

        call.enqueue(object : Callback<Character> {

            override fun onFailure(call: Call<Character>, t: Throwable) {
                //  Toast.makeText(context,t.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                var character: Character?=response!!.body()

                /*
                * In this section :
                * 1 - All the characters details fetch from Api
                * 2- Create a characterEntity model for each character and save in the Database
                */
                var id= (item.substring(28,item.length-1)).toInt()
                var mycharacter = CharacterEntity(id,character!!.name, character.gender,
                    character.birth_year, character.height, character.mass,
                    character.hair_color, character.skin_color, character.eye_color,
                    character.homeworld, character.films.toString(),
                    character.species.toString(), character.starships.toString(),
                    character.vehicles.toString(), character.url)

                if (getCharacterbyId(id) == null)
                    insertCharacter(mycharacter)
            }
        })
    }
}




