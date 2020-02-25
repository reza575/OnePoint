package com.moeiny.reza.onepoint.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moeiny.reza.onepoint.databinding.FilmBinding
import com.moeiny.reza.onepoint.repository.model.FilmShow
import com.moeiny.reza.onepoint.utils.CustomClickListener
import com.moeiny.reza.onepoint.view.ShowActivity


class FilmAdapter(var context: Context, var filmList:List<FilmShow>): RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() ,
    CustomClickListener {

       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val filmBinding = FilmBinding.inflate(layoutInflater, parent, false)
        return FilmViewHolder(filmBinding)
    }

    override fun getItemCount(): Int {
        return filmList.count()
    }

    override fun onBindViewHolder(holderFilm: FilmViewHolder, position: Int) {
        var film=filmList.get(position)

        holderFilm.bind(film)
        holderFilm.filmBinding.setItemClickListener(this)
    }

    inner class FilmViewHolder(val filmBinding: FilmBinding): RecyclerView.ViewHolder(filmBinding.root){
      fun bind(modelView: FilmShow) {
          this.filmBinding.filmshow = modelView
      }
    }

    override fun cardClicked(film: FilmShow) {
        val intent = Intent(context, ShowActivity::class.java)
        intent.putExtra("filmId", film.episode_id.toString())
        context!!.startActivity(intent)

       }

    @BindingAdapter("imageUrl")
    fun ImageView.setImageUrl(url: String?) {
        Glide.with(context).load(url).into(this)
    }

}