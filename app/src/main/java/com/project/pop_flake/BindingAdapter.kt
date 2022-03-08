package com.project.pop_flake

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import android.net.NetworkInfo

import androidx.test.core.app.ApplicationProvider.getApplicationContext

import android.net.ConnectivityManager
import androidx.test.core.app.ApplicationProvider


@BindingAdapter("movieImageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Picasso.get()
            .load(imgUrl)
            .error(R.drawable.ic_broken_image_24)
            .into(imgView)

    }
}

