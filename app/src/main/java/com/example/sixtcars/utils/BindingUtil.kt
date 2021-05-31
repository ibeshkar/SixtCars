package com.example.sixtcars.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.sixtcars.R

object BindingUtil {

    @JvmStatic
    @BindingAdapter("app:link")
    fun showImage(view: AppCompatImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(RequestOptions().error(R.drawable.place_holder))
            .into(view)
    }
}