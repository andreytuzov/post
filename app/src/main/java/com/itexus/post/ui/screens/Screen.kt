package com.itexus.post.ui.screens

import android.os.Bundle

sealed class Screen(val route: String) {
    object Posts : Screen("posts")
    object PostDetails : Screen("post/{id}") {
        fun createRoute(postId: Long) = "post/${postId}"

        val Bundle.postId: Long?
            get() = getString("id")?.toLong()
    }
}