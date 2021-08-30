package com.itexus.post.module

import com.itexus.post.ui.screens.post_detail.BasePostViewModel
import com.itexus.post.ui.screens.post_detail.PostViewModel
import com.itexus.post.ui.screens.posts.BasePostsViewModel
import com.itexus.post.ui.screens.posts.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModels = module {

    viewModel<BasePostsViewModel> {
        PostsViewModel(
            repository = get()
        )
    }

    viewModel<BasePostViewModel> { (postId: Long) ->
        PostViewModel(
            repository = get(),
            postId = postId
        )
    }
}