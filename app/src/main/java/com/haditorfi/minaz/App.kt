package com.haditorfi.minaz

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import com.facebook.drawee.backends.pipeline.Fresco
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.data.customer.CustomerRepository
import com.haditorfi.minaz.data.customer.CustomerRepositoryImpl
import com.haditorfi.minaz.data.db.AppDatabase
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.data.service.ServiceRepository
import com.haditorfi.minaz.data.service.ServiceRepositoryImpl
import com.haditorfi.minaz.feature.customer.CustomerViewModel
import com.haditorfi.minaz.feature.service.ServiceFragment
import com.haditorfi.minaz.feature.service.ServiceViewModel
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

        val appModules = module {
            single<ImageLoadingService> { FrescoImageLoadingService() }
            single {
                Room.databaseBuilder(
                    this@App,
                    AppDatabase::class.java, "db-minaz"
                )
                    .build()
            }
            single<SharedPreferences> {
                this@App.getSharedPreferences(
                    "app_settings",
                    MODE_PRIVATE
                )
            }

            factory { Customer(0, "", "", "", false) }

            factory { Service(0, "", "", "", false) }

            factory<CustomerRepository> { CustomerRepositoryImpl(get<AppDatabase>().customerDao()) }

            factory<ServiceRepository> { ServiceRepositoryImpl(get<AppDatabase>().serviceDao()) }

            viewModel { CustomerViewModel(get()) }

            viewModel { ServiceViewModel(get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(appModules)
        }
    }
}