package com.itexus.post.domain.model

data class Post(
    val id: Long,
    val user: User,
    val title: String,
    val body: String,
    val comments: List<Comment>
)