package com.itexus.post.module

import com.itexus.post.data.LocalPostDataSource
import com.itexus.post.data.PostRepositoryImpl
import com.itexus.post.data.RemotePostDataSource
import com.itexus.post.data.local.LocalPostDataSourceImpl
import com.itexus.post.data.local.mapper.LocalPostMapper
import com.itexus.post.data.remote.RemotePostDataSourceImpl
import com.itexus.post.data.remote.mapper.RemotePostMapper
import com.itexus.post.domain.PostRepository
import org.koin.dsl.module

internal val repositoryModule = module {

    factory<LocalPostMapper> { LocalPostMapper.Impl() }
    factory<RemotePostMapper> { RemotePostMapper.Impl() }

    single<LocalPostDataSource> {
        LocalPostDataSourceImpl(
            dao = get(),
            mapper = get()
        )
    }

    single<RemotePostDataSource> {
        RemotePostDataSourceImpl(
            userApi = get(),
            postApi = get(),
            mapper = get()
        )
    }

    single<PostRepository> {
        PostRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }
}