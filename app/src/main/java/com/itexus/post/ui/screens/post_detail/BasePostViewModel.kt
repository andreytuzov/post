package com.itexus.post.ui.screens.post_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.itexus.post.domain.model.Post

abstract class BasePostViewModel : ViewModel() {
    abstract val post: LiveData<Post>
}