package com.moeiny.reza.deloittest.repository.database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Films")
class FilmEntity (@PrimaryKey   var episode_id:Int,
                  @ColumnInfo var created: String,
                  @ColumnInfo var director: String,
                  @ColumnInfo var edited: String,
                  @ColumnInfo var opening_crawl: String,
                  @ColumnInfo var producer: String,
                  @ColumnInfo var release_date: String,
                  @ColumnInfo var title: String,
                  @ColumnInfo var characters: String,
                  @ColumnInfo var planets: String,
                  @ColumnInfo var species: String,
                  @ColumnInfo var starships: String,
                  @ColumnInfo var vehicles: String,
                  @ColumnInfo var url: String
)

