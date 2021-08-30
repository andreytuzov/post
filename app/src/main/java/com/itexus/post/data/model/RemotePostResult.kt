package com.itexus.post.data.model

import com.itexus.post.domain.model.Post

sealed class RemotePostResult {
    data class Success(val posts: List<Post>) : RemotePostResult()
    object NetworkConnectionError : RemotePostResult()
    object NotFoundException : RemotePostResult()
}