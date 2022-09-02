package com.magdev.infrastructure.extensions

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.getCompatColor(colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}

fun Context.getCompatDrawable(@DrawableRes drawableRes: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawableRes)
}

inline fun <reified T: Any> Context.intentFor(): Intent {
    return Intent(this, T::class.java)
}

fun Context.longToast(message: CharSequence): Toast = Toast
    .makeText(this, message, Toast.LENGTH_LONG)
    .apply {
        show()
    }

fun Context.toast(message: CharSequence): Toast = Toast
    .makeText(this, message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }