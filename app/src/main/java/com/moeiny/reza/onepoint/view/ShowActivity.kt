package com.moeiny.reza.onepoint.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.deloittest.repository.database.entitiy.FilmEntity
import com.moeiny.reza.nfoxsport.adapter.CharacterAdapter
import com.moeiny.reza.onepoint.R
import com.moeiny.reza.onepoint.viewmodel.FilmsViewModel


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
        /*
        * Initialise views
       */
        setUpView()

        /*
         * get the data that passed from film Adapter when clicked on eah film item on MainActivity recyclerView
        */
        val bundle = intent.extras
        var film_Id = bundle?.getString("filmId")
        var film=viewModel.getFilmsByEposodeId(film_Id!!.toInt())

        loadData(film)   // assign the views on ShowActivity with specific values
    }

    fun loadData(film:FilmEntity){
        txtTitle.text = film.title
        txtDirector.text = film.director
        txtProdeucer.text = film.producer
        txtReleased.text = film.release_date
        txtTitle.text = film.title

        var myList= film.characters.substring(1,film.characters.length-1)

        /*
        * Split All the character item  from String valye to an String Array
        */
        var result: List<String> = myList.split(",").map { it.trim() }

        /*
        * Extract the names of Character to show on character recyclerView
        */
        val items = ArrayList<String>()
        for(i in 0..result.size-1){
            var name=result.get(i)
            var c=name.length
            var id= (name.substring(28,name.length-1)).toInt()
            items.add(viewModel.getCharacterById(id).name)
        }

        setDataOnRecycler(items) // show the names of Characters of movie that clicked on MainActivity recyclerView
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
