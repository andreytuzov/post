package com.itexus.post.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class PostWithCommentAndUser(
    @Embedded
    val post: PostEntity,
    @Relation(parentColumn = "user_id", entityColumn = "id")
    val user: UserEntity,
    @Relation(parentColumn = "id", entityColumn = "post_id")
    val comments: List<CommentEntity>
)