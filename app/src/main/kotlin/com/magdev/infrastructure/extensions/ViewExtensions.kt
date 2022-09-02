package com.magdev.infrastructure.extensions

import android.view.View

fun View.onClick(l: (v: View?) -> Unit) {
    setOnClickListener(l)
}