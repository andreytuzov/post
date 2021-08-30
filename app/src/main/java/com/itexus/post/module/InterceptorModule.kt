package com.itexus.post.module

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import timber.log.Timber

internal val interceptorModule = module {
    single<Interceptor> { provideLoggingInterceptor() }
}

private fun provideLoggingInterceptor(): Interceptor {
    val logger = object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Timber.d("OkHttp -> $message")
        }
    }
    return HttpLoggingInterceptor(logger)
        .apply { level = HttpLoggingInterceptor.Level.BODY }
}