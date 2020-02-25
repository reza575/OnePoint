package com.moeiny.reza.onepoint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.deloittest.repository.database.entitiy.FilmEntity
import com.moeiny.reza.onepoint.view.adapter.FilmAdapter
import com.moeiny.reza.onepoint.repository.model.FilmShow
import com.moeiny.reza.onepoint.viewmodel.FilmsViewModel

class MainActivity : AppCompatActivity() {

    lateinit var filmList:ArrayList<FilmEntity>
    lateinit var showList:ArrayList<FilmShow>
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: FilmsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        * Initialise views
         */
        setUpView()

        filmList=viewModel.getAllFilms() as ArrayList<FilmEntity>  // get All the Movie information from Database

        /*
        * Loading Data to main recycler view to show in MainAcivity
         */
        loadData()
    }

    fun loadData(){

        showList.clear()     // delete All data in showlist to prepare it to fill by update view objects to send to recyclerView

        /*
         * filling showlist with the movie information from dtabase to sennd to recyclerView
          */
        for ( i in 0..filmList.size-1){
            var show= FilmShow(
                filmList[i].episode_id,
                filmList[i].title,
                filmList[i].release_date
            )
            showList.add(show)
        }

        setDataOnRecycler(showList)           // Send data to recyclrView
    }

    fun setDataOnRecycler(filmList:List<FilmShow>) {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = FilmAdapter(this,filmList)
    }

    fun setUpView() {
        filmList = ArrayList<FilmEntity>()
        showList = ArrayList<FilmShow>()
        viewModel = ViewModelProviders.of(this).get(FilmsViewModel::class.java)
        recyclerView = findViewById(R.id.rv_main_lastnews)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
