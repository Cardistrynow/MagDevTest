package com.magdev.infrastructure.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadWeatherIcon(iconId: String) {
    Glide.with(context)
        .load("http://openweathermap.org/img/wn/$iconId@2x.png")
        .into(this)
}