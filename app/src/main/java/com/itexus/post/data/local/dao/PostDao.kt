package com.itexus.post.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.itexus.post.data.local.model.CommentEntity
import com.itexus.post.data.local.model.PostEntity
import com.itexus.post.data.local.model.PostWithCommentAndUser
import com.itexus.post.data.local.model.UserEntity

@Dao
abstract class PostDao {

    @Query("SELECT * FROM post")
    abstract fun getPosts(): LiveData<List<PostWithCommentAndUser>>

    @Query("SELECT * FROM post WHERE id = :postId")
    abstract fun getPost(postId: Long): LiveData<PostWithCommentAndUser>

    @Transaction
    open fun replaceData(posts: List<PostWithCommentAndUser>) {
        clearAll()
        saveUsers(posts.map { it.user })
        savePosts(posts.map { it.post })
        saveComments(posts.map { it.comments }.flatten())
    }

    @Transaction
    open fun clearAll() {
        clearUsers()
        clearComments()
        clearPosts()
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveUsers(users: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveComments(comments: List<CommentEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun savePosts(posts: List<PostEntity>)

    @Query("DELETE FROM user")
    abstract fun clearUsers()

    @Query("DELETE FROM comment")
    abstract fun clearComments()

    @Query("DELETE FROM post")
    abstract fun clearPosts()
}