package com.itexus.post.ui.screens.post_detail

import com.itexus.post.domain.PostRepository

class PostViewModel(
    repository: PostRepository,
    postId: Long
) : BasePostViewModel() {

    override val post = repository.getPost(postId)
}