package com.haditorfi.minaz

import android.app.Application
import androidx.room.Room
import com.haditorfi.minaz.common.FakeData
import com.haditorfi.minaz.data.customer.CustomerRepository
import com.haditorfi.minaz.data.customer.CustomerRepositoryImpl
import com.haditorfi.minaz.data.db.AppDatabase
import com.haditorfi.minaz.data.product.ProductRepository
import com.haditorfi.minaz.data.product.ProductRepositoryImpl
import com.haditorfi.minaz.data.service.ServiceRepository
import com.haditorfi.minaz.data.service.ServiceRepositoryImpl
import com.haditorfi.minaz.data.service.provide.ProvideServiceRepository
import com.haditorfi.minaz.data.service.provide.ProvideServiceRepositoryImpl
import com.haditorfi.minaz.data.staff.StaffRepository
import com.haditorfi.minaz.data.staff.StaffRepositoryImpl
import com.haditorfi.minaz.feature.customer.CustomerViewModel
import com.haditorfi.minaz.feature.dashboard.DashboardViewModel
import com.haditorfi.minaz.feature.product.ProductViewModel
import com.haditorfi.minaz.feature.services.provide.ProvideServiceViewModel
import com.haditorfi.minaz.feature.services.service.ServiceViewModel
import com.haditorfi.minaz.feature.staff.StaffViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        val appModules = module {
            single {
                Room.databaseBuilder(
                    this@App,
                    AppDatabase::class.java, "db-minaz"
                )
                    .build()
            }
        }

        val repositoryModules = module {

            factory<CustomerRepository> { CustomerRepositoryImpl(get<AppDatabase>().customerDao()) }

            factory<ServiceRepository> { ServiceRepositoryImpl(get<AppDatabase>().serviceDao()) }

            factory<ProvideServiceRepository> { ProvideServiceRepositoryImpl(get<AppDatabase>().provideServiceDao()) }

            factory<ProductRepository> { ProductRepositoryImpl(get<AppDatabase>().productDao()) }

            factory<StaffRepository> { StaffRepositoryImpl(get<AppDatabase>().personnelDao()) }
        }

        val viewModelModules = module {

            viewModel { DashboardViewModel() }

            viewModel { CustomerViewModel(get()) }

            viewModel { ServiceViewModel(get()) }

            viewModel { ProvideServiceViewModel(get()) }

            viewModel { ProductViewModel(get()) }

            viewModel { StaffViewModel(get()) }
        }

        val classModules = module {
            factory { FakeData(get(), get(), get(), get(), get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(appModules, repositoryModules, viewModelModules, classModules)
        }
    }
}