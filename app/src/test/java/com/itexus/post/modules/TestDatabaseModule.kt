package com.itexus.post.modules

import com.itexus.post.data.local.dao.PostDao
import com.itexus.post.modules.fake.FakePostDao
import org.koin.dsl.module

internal val testDatabaseModule = module {
    single<PostDao> { FakePostDao() }
}