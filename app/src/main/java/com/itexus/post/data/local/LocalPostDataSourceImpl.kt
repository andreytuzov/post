package com.itexus.post.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.itexus.post.data.LocalPostDataSource
import com.itexus.post.data.local.dao.PostDao
import com.itexus.post.data.local.mapper.LocalPostMapper
import com.itexus.post.domain.model.Post

class LocalPostDataSourceImpl(
    private val dao: PostDao,
    private val mapper: LocalPostMapper
) : LocalPostDataSource {

    override fun getPosts(): LiveData<List<Post>> {
        return Transformations.map(dao.getPosts()) {
            it.map(mapper::map)
        }
    }

    override fun getPost(postId: Long): LiveData<Post> {
        return Transformations.map(dao.getPost(postId), mapper::map)
    }

    override suspend fun savePosts(posts: List<Post>) {
        dao.replaceData(posts.map(mapper::map))
    }
}

