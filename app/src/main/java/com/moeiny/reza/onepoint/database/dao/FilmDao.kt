package com.moeiny.reza.onepoint.database.dao

import androidx.room.*
import com.moeiny.reza.deloittest.repository.database.entitiy.FilmEntity

@Dao
interface FilmDao {

    @Query("SELECT * FROM Films ORDER BY release_date Desc")
    fun getAll(): List<FilmEntity>

    @Query("SELECT * FROM Films WHERE episode_id = :id ")
    fun findByid(id: Int): FilmEntity

    @Query("DELETE FROM Films")
    fun deleteAll()

    @Insert
    fun insert(film: FilmEntity)

    @Update
    fun update(film: FilmEntity)

    @Delete
    fun delete(film: FilmEntity)
}




