package com.itexus.post.modules.fake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.itexus.post.data.local.dao.PostDao
import com.itexus.post.data.local.model.CommentEntity
import com.itexus.post.data.local.model.PostEntity
import com.itexus.post.data.local.model.PostWithCommentAndUser
import com.itexus.post.data.local.model.UserEntity

class FakePostDao : PostDao() {

    private val cache = MutableLiveData<List<PostWithCommentAndUser>>()

    override fun getPosts(): LiveData<List<PostWithCommentAndUser>> {
        return cache
    }

    override fun getPost(postId: Long): LiveData<PostWithCommentAndUser> {
        return Transformations.map(cache) {
            it.first { it.post.id == postId }
        }
    }

    override fun replaceData(posts: List<PostWithCommentAndUser>) {
        cache.postValue(posts)
    }

    override fun clearUsers() {
        cache.postValue(emptyList())
    }

    override fun saveUsers(users: List<UserEntity>) = Unit
    override fun saveComments(comments: List<CommentEntity>) = Unit
    override fun savePosts(posts: List<PostEntity>) = Unit
    override fun clearComments() = Unit
    override fun clearPosts() = Unit
}