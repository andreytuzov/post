package com.itexus.post.module

import com.google.gson.Gson
import com.itexus.post.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal val networkModule = module {
    single { provideGson() }
    single { provideOkHttpClient(interceptors = getAll()) }
    single {
        provideRetrofit(
            client = get(),
            gson = get()
        )
    }
}

fun provideGson(): Gson {
    return Gson()
}

private const val TIMEOUT_SEC = 20L
fun provideOkHttpClient(interceptors: List<Interceptor>): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .apply {
            interceptors.forEach { addInterceptor(it) }
        }
        .build()
}

fun provideRetrofit(
    client: OkHttpClient,
    gson: Gson
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}