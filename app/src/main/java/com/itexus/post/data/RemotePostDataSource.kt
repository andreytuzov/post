package com.itexus.post.data

import com.itexus.post.data.model.RemotePostResult

interface RemotePostDataSource {
    suspend fun getPosts(): RemotePostResult
}