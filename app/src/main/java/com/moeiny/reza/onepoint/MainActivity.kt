package com.moeiny.reza.onepoint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.deloittest.repository.database.entitiy.FilmEntity
import com.moeiny.reza.onepoint.adapter.FilmAdapter
import com.moeiny.reza.onepoint.model.FilmShow
import com.moeiny.reza.onepoint.viewmodel.FilmsViewModel

class MainActivity : AppCompatActivity() {

    lateinit var filmList:ArrayList<FilmEntity>
    lateinit var showList:ArrayList<FilmShow>
    lateinit var recyclerView: RecyclerView

    lateinit var viewModel: FilmsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        filmList = ArrayList<FilmEntity>()
        showList = ArrayList<FilmShow>()
        setUpView()
        filmList=viewModel.getAllFilms() as ArrayList<FilmEntity>
        loadData()
    }

    fun loadData(){

        showList.clear()
        for ( i in 0..filmList.size-1){
            var show= FilmShow(
                filmList[i].episode_id,
                filmList[i].title,
                filmList[i].release_date
            )
            showList.add(show)
        }

        setDataOnRecycler(showList)
    }

    fun setDataOnRecycler(filmList:List<FilmShow>) {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = FilmAdapter(this,filmList)
    }

    fun setUpView() {
        viewModel = ViewModelProviders.of(this).get(FilmsViewModel::class.java)
        recyclerView = findViewById(R.id.rv_main_lastnews)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
