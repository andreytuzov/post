package com.itexus.post.ui.screens.posts

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itexus.post.domain.model.Post

abstract class BasePostsViewModel : ViewModel() {
    abstract val posts: LiveData<List<Post>>
    abstract val isLoading: LiveData<Boolean>

    abstract val error: MutableLiveData<Error>

    data class Error(
        @StringRes val screenError: Int
    )

    abstract fun refresh()
}