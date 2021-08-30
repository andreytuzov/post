package com.itexus.post.domain

import androidx.lifecycle.LiveData
import com.itexus.post.domain.model.Post
import com.itexus.post.domain.model.RefreshResult

interface PostRepository {
    suspend fun refresh(): RefreshResult
    fun getPosts(): LiveData<List<Post>>
    fun getPost(id: Long): LiveData<Post>
}