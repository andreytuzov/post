package com.itexus.post.ui.utils

fun String.brief(count: Int = 100): String {
    return if (length > count) take(count) + "..."
    else this
}