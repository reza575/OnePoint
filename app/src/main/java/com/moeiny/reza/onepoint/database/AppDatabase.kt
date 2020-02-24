package com.moeiny.reza.onepoint.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.moeiny.reza.deloittest.repository.database.entitiy.CharacterEntity
import com.moeiny.reza.deloittest.repository.database.entitiy.FilmEntity
import com.moeiny.reza.onepoint.database.dao.CharacterDao
import com.moeiny.reza.onepoint.database.dao.FilmDao


@Database(entities = [(FilmEntity::class), (CharacterEntity::class)], version = 1, exportSchema = false)

public abstract class AppDatabase : RoomDatabase() {

    abstract fun FilmDao(): FilmDao
    abstract fun CharacterDao(): CharacterDao


    companion object {

        private var instance: AppDatabase? = null
        public var DB_NAME = "Films"

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance!!
        }
    }

    private val roomCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            PopulateDbAsyncTask(instance)
                    .execute()
        }
    }

}

    class PopulateDbAsyncTask(db: AppDatabase?) : AsyncTask<Unit, Unit, Unit>() {
    private val filmDao = db?.FilmDao()

    override fun doInBackground(vararg p0: Unit?) {
       // FilmDao?.insert(FilmEntity("", "","","","","",""))

      }
    }

