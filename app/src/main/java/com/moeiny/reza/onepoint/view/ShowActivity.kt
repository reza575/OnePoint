package com.moeiny.reza.onepoint.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.moeiny.reza.deloittest.repository.database.entitiy.FilmEntity
import com.moeiny.reza.nfoxsport.adapter.CharacterAdapter
import com.moeiny.reza.onepoint.R
import com.moeiny.reza.onepoint.viewmodel.FilmsViewModel
import org.json.JSONArray
import java.net.MalformedURLException
import java.net.URL
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList


class ShowActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var txtTitle: TextView
    lateinit var txtDirector: TextView
    lateinit var txtProdeucer: TextView
    lateinit var txtReleased: TextView
    lateinit var viewModel: FilmsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        setUpView()
        val bundle = intent.extras
        var film_Id = bundle?.getString("filmId")
        var film=viewModel.getFilmsByEposodeId(film_Id!!.toInt())
        loadData(film)
    }


    fun loadData(film:FilmEntity){
        txtTitle.text = film.title
        txtDirector.text = film.director
        txtProdeucer.text = film.producer
        txtReleased.text = film.release_date
        txtTitle.text = film.title

        var myList= film.characters.substring(1,film.characters.length-1)

        var result: List<String> = myList.split(",").map { it.trim() }

        val items = ArrayList<String>()
        for(i in 0..result.size-1){
            var name=result.get(i)
            var c=name.length
            var id= (name.substring(28,name.length-1)).toInt()
            items.add(viewModel.getCharacterById(id).name)
        }

        setDataOnRecycler(items)
    }

    fun setDataOnRecycler(items:ArrayList<String>) {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = CharacterAdapter(this, items)
    }

    fun setUpView() {
        viewModel = ViewModelProviders.of(this).get(FilmsViewModel::class.java)
        txtTitle = findViewById(R.id.txt_showactivity_titleinfo)
        txtDirector = findViewById(R.id.txt_showactivity_directorinfo)
        txtProdeucer = findViewById(R.id.txt_showactivity_prodeucerinfo)
        txtReleased = findViewById(R.id.txt_showactivity_releasedinfo)
        recyclerView = findViewById(R.id.rv_showactivity_characters)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}
