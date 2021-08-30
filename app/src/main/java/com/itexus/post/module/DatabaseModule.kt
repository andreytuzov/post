package com.itexus.post.module

import android.content.Context
import androidx.room.Room
import com.itexus.post.data.local.PostDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val databaseModule = module {
    single { provideDatabase(androidContext()) }
    single { get<PostDataBase>().postDao() }
}

private const val DB_NAME = "posts.db"
private fun provideDatabase(context: Context): PostDataBase {
    return Room.databaseBuilder(context, PostDataBase::class.java, DB_NAME)
        .build()
}