package com.haditorfi.minaz

import android.app.Application
import android.content.SharedPreferences
import com.facebook.drawee.backends.pipeline.Fresco
import com.haditorfi.minaz.services.FrescoImageLoadingService
import com.haditorfi.minaz.services.ImageLoadingService
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Fresco.initialize(this)

        val appModule = module {
            single<ImageLoadingService> { FrescoImageLoadingService() }
            single<SharedPreferences> {
                this@App.getSharedPreferences(
                    "app_settings",
                    MODE_PRIVATE
                )
            }
        }

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}