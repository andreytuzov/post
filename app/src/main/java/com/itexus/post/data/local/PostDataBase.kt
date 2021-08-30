package com.itexus.post.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itexus.post.data.local.dao.PostDao
import com.itexus.post.data.local.model.CommentEntity
import com.itexus.post.data.local.model.PostEntity
import com.itexus.post.data.local.model.UserEntity

@Database(
    entities = [UserEntity::class, PostEntity::class, CommentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PostDataBase : RoomDatabase() {
    abstract fun postDao(): PostDao
}