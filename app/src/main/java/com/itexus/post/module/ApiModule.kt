package com.itexus.post.module

import com.itexus.post.data.remote.api.PostApi
import com.itexus.post.data.remote.api.UserApi
import com.itexus.post.data.remote.utils.provideApi
import org.koin.dsl.module

internal val apiModule = module {
    single<PostApi> { provideApi(retrofit = get()) }
    single<UserApi> { provideApi(retrofit = get()) }
}