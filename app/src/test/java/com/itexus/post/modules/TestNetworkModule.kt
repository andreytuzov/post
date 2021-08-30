package com.itexus.post.modules

import com.google.gson.Gson
import com.itexus.post.module.provideGson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal val testNetworkModule = module {
    single { provideGson() }
    single { MockWebServer() }
    single { provideOkHttpClient(interceptors = getAll()) }
    single {
        provideRetrofit(
            client = get(),
            gson = get(),
            mockWebServer = get()
        )
    }
}

private const val TIMEOUT_SEC = 1L
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

private fun provideRetrofit(
    gson: Gson,
    client: OkHttpClient,
    mockWebServer: MockWebServer
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}