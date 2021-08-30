package com.itexus.post.data.remote.api

import com.itexus.post.data.remote.model.CommentResponse
import com.itexus.post.data.remote.model.PostResponse
import retrofit2.http.GET

interface PostApi {

    @GET("posts")
    suspend fun getPosts(): List<PostResponse>

    @GET("comments")
    suspend fun getComments(): List<CommentResponse>
}