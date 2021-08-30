package com.itexus.post

import android.app.Application
import com.itexus.post.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class PostApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@PostApp)
            modules(
                networkModule,
                interceptorModule,
                apiModule,
                repositoryModule,
                viewModels,
                databaseModule
            )
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}