package com.moeiny.reza.onepoint.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.moeiny.reza.deloittest.repository.database.entitiy.CharacterEntity
import com.moeiny.reza.deloittest.repository.database.entitiy.FilmEntity
import com.moeiny.reza.onepoint.API.service.FilmsService
import com.moeiny.reza.onepoint.model.Character
import com.moeiny.reza.onepoint.model.Films
import com.moeiny.reza.onepoint.model.Result
import com.moeiny.reza.onepoint.repository.FilmsRepository
import com.moeiny.reza.onepoint.utils.FilmsCallback
import com.moeiny.reza.onepoint.view.HomeActivity
import java.util.concurrent.CountDownLatch

class FilmsViewModel(application: Application) : AndroidViewModel(application) {

    private  var filmRepository: FilmsRepository
    private  var allFilmsData:List<FilmEntity>
    private  var allCharactersData:List<CharacterEntity>


    init {
        filmRepository= FilmsRepository(application)
        allFilmsData=filmRepository.getAllFilms()
        allCharactersData=filmRepository.getAllCharacters()
    }


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

///////////////////////////////////////////////////

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

/////////////////////////////////////////////////////////////

fun getFilmsInfo(){

       lateinit var resultList:ArrayList<Result>
       lateinit var characterList:ArrayList<String>
       lateinit var films:Films
       val lock =  CountDownLatch(1)

    FilmsService.getFilmsInfo(object : FilmsCallback<Films, Throwable> {

            override fun onSuccess(result: Films) {
                lock.countDown()
                resultList = ArrayList<Result>()
                characterList = ArrayList<String>()
                films = result
                resultList=films.results as ArrayList<Result>

                for (i in 0..resultList.size-1) {
                    characterList=resultList[i].characters as ArrayList<String>

                    var film = FilmEntity(resultList[i].episode_id, resultList[i].created,
                        resultList[i].director, resultList[i].edited, resultList[i].opening_crawl,
                        resultList[i].producer, resultList[i].release_date, resultList[i].title,
                        resultList[i].characters.toString(), resultList[i].planets.toString(),
                        resultList[i].species.toString(), resultList[i].starships.toString(),
                        resultList[i].vehicles.toString(), resultList[i].url)

                    if (getFilmsByEposodeId(resultList[i].episode_id) == null)
                        insert(film)

                    for(j in 0..characterList.size-1){
                        var item=characterList[j]
                        var lenght=item.length

                        var id= (item.substring(28,item.length-1)).toInt()


                        getCharacterInfo(id)
                    }
                }
            }

            override fun onError(error: Throwable?) {
                lock.countDown()
            }

            override fun onComplete() {
                lock.countDown()
                print("complete")
                var view= HomeActivity.getView()
                view.visibility= View.INVISIBLE
                HomeActivity.move()
            }
        })
    }


    fun getCharacterInfo(id:Int) {

        val lock =  CountDownLatch(1)
        lateinit var character: Character

        FilmsService.getCharactersInfo(id,object : FilmsCallback<Character, Throwable> {

            override fun onSuccess(result: Character) {
                character = result
                lock.countDown()
                var mycharacter = CharacterEntity(id,character.name, character.gender,
                character.birth_year, character.height, character.mass,
                character.hair_color, character.skin_color, character.eye_color,
                character.homeworld, character.films.toString(),
                character.species.toString(), character.starships.toString(),
                character.vehicles.toString(), character.url)

                if (getCharacterById(id) == null)
                    insert(mycharacter)
            }

            override fun onError(error: Throwable?) {
                lock.countDown()
            }

            override fun onComplete() {
                print("complete")
                lock.countDown()
            }
        })

    }

 }