package com.moeiny.reza.onepoint.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

    /*
     * this object is essential to showing image on ImageView for viewitems of ViewAdapter
        when we use databinding
    */

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, url: String) {
        Glide.with(view.context).load(url).into(view)
    }
}