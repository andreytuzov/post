package com.itexus.post.data

import androidx.lifecycle.LiveData
import com.itexus.post.data.model.RemotePostResult
import com.itexus.post.domain.PostRepository
import com.itexus.post.domain.model.Post
import com.itexus.post.domain.model.RefreshResult

class PostRepositoryImpl(
    private val remoteDataSource: RemotePostDataSource,
    private val localDataSource: LocalPostDataSource
) : PostRepository {

    override suspend fun refresh(): RefreshResult {
        return when (val result = remoteDataSource.getPosts()) {
            is RemotePostResult.NetworkConnectionError,
            is RemotePostResult.NotFoundException -> RefreshResult.NetworkConnectionError
            is RemotePostResult.Success -> {
                localDataSource.savePosts(result.posts)
                RefreshResult.Success
            }
        }
    }

    override fun getPosts(): LiveData<List<Post>> {
        return localDataSource.getPosts()
    }

    override fun getPost(id: Long): LiveData<Post> {
        return localDataSource.getPost(id)
    }
}