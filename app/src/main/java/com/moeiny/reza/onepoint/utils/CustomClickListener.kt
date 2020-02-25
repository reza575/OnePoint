package com.moeiny.reza.onepoint.utils

import com.moeiny.reza.onepoint.repository.model.FilmShow

    /*
     * this interface is essential to enabling clicklistener action for viewitems of ViewAdapter
        when we use databinding
    */

interface CustomClickListener {
    fun cardClicked(f: FilmShow)
}