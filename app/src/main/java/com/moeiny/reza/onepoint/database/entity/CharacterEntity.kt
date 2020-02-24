package com.moeiny.reza.deloittest.repository.database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Characters")
class CharacterEntity (@PrimaryKey   var id:Int,
                       @ColumnInfo var name: String,
                       @ColumnInfo var gender: String,
                       @ColumnInfo var birthyear: String,
                       @ColumnInfo var height: String,
                       @ColumnInfo var mass: String,
                       @ColumnInfo var haircolor: String,
                       @ColumnInfo var skincolor: String,
                       @ColumnInfo var eycolor: String,
                       @ColumnInfo var homeworld: String,
                       @ColumnInfo var films: String,
                       @ColumnInfo var species: String,
                       @ColumnInfo var starships: String,
                       @ColumnInfo var vehicles: String,
                       @ColumnInfo var url: String
)

