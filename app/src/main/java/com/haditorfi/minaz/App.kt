package com.haditorfi.minaz

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import com.facebook.drawee.backends.pipeline.Fresco
import com.haditorfi.minaz.data.customer.CustomerRepository
import com.haditorfi.minaz.data.customer.CustomerRepositoryImpl
import com.haditorfi.minaz.data.db.AppDatabase
import com.haditorfi.minaz.feature.customer.CustomerViewModel
import com.haditorfi.minaz.feature.dashboard.DashboardViewModel
import com.haditorfi.minaz.services.FrescoImageLoadingService
import com.haditorfi.minaz.services.ImageLoadingService
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
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
            single {
                Room.databaseBuilder(
                    this@App,
                    AppDatabase::class.java, "db-minaz"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            single<SharedPreferences> {
                this@App.getSharedPreferences(
                    "app_settings",
                    MODE_PRIVATE
                )
            }

            factory<CustomerRepository> { CustomerRepositoryImpl(get<AppDatabase>().customerDao()) }

            viewModel { CustomerViewModel(get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}