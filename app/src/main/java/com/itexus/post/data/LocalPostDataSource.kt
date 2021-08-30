package com.itexus.post.data

import androidx.lifecycle.LiveData
import com.itexus.post.domain.model.Post

interface LocalPostDataSource {
    fun getPosts(): LiveData<List<Post>>
    fun getPost(postId: Long): LiveData<Post>
    suspend fun savePosts(posts: List<Post>)
}