package com.app.goustotask.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.goustotask.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.image_placeholder)
            ).override(Target.SIZE_ORIGINAL)
            .into(view)
    }
}

@BindingAdapter("setPrice")
fun setPrice(view: TextView, price: String?) {
   price?.let {
       view.text = "Price: $price"
   }
}

@BindingAdapter("setCategory")
fun setCategories(view: TextView, category: String?) {
    category?.let {
        view.text = "Categories: \n$category"
    }
}

@BindingAdapter("setVolume")
fun setVolume(view: TextView, volume: String?) {
    volume?.let {
        view.text = "Volume: $volume"
    }
}

@BindingAdapter("setZone")
fun setZone(view: TextView, zone: String?) {
    zone?.let {
        view.text = "Zone: $zone"
    }
}