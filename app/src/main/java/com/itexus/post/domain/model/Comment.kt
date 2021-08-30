package com.itexus.post.domain.model

data class Comment(
    val id: Long,
    val name: String,
    val email: String,
    val body: String
)