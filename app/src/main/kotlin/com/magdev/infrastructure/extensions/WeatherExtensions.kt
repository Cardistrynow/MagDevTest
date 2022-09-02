package com.magdev.infrastructure.extensions

fun Int.formatDegree(): String {
    return when {
        this > 0 -> "+$this"
        this < 0 -> "-$this"
        else -> this.toString()
    }
}