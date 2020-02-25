package com.moeiny.reza.nfoxsport.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.onepoint.R

class CharacterAdapter(var context: Context, var characterList:ArrayList<String>): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.characterrow,parent,false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characterList.count()
    }

    override fun onBindViewHolder(holderCharacter: CharacterViewHolder, position: Int) {
        var characterRow=characterList.get(position)
        holderCharacter.txtname.setText(characterRow)

        holderCharacter.parent.setOnClickListener(){
              // we don't need any action when click on view item in this case
        }
    }

    inner class CharacterViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){
        var txtname= itemView!!.findViewById<TextView>(R.id.txt_characterrow_name)
        var imgline=itemView!!.findViewById<ImageView>(R.id.img_statrow_line)
        var parent=itemView!!.findViewById<RelativeLayout>(R.id.rl_statrow_parent)
    }

}