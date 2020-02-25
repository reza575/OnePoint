package com.moeiny.reza.onepoint.repository.model.database.dao

import androidx.room.*
import com.moeiny.reza.deloittest.repository.database.entitiy.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM Characters  ")
    fun getAll(): List<CharacterEntity>

    @Query("SELECT * FROM Characters WHERE id = :id ")
    fun findByid(id: Int): CharacterEntity

    @Query("DELETE FROM Characters")
    fun deleteAll()

    @Insert
    fun insert(character: CharacterEntity)

    @Update
    fun update(character: CharacterEntity)

    @Delete
    fun delete(character: CharacterEntity)
}




