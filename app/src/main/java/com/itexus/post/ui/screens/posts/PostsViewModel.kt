package com.itexus.post.ui.screens.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.itexus.post.R
import com.itexus.post.domain.PostRepository
import com.itexus.post.domain.model.Post
import com.itexus.post.domain.model.RefreshResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostsViewModel(
    private val repository: PostRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BasePostsViewModel() {

    override val posts: LiveData<List<Post>> = repository.getPosts()
    override val isLoading = MutableLiveData(false)
    override val error = MutableLiveData<Error>()

    init {
        refresh()
    }

    override fun refresh() {
        viewModelScope.launch {
            error.value = null
            isLoading.value = true
            val result = withContext(ioDispatcher) {
                repository.refresh()
            }
            isLoading.value = false
            handleResult(result)
        }
    }

    private fun handleResult(result: RefreshResult) {
        if (result is RefreshResult.NetworkConnectionError) {
            error.value = Error(R.string.loading_error)
        }
    }
}