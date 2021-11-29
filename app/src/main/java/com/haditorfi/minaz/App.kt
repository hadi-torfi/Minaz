package com.haditorfi.minaz

import android.app.Application
import androidx.room.Room
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.data.customer.CustomerRepository
import com.haditorfi.minaz.data.customer.CustomerRepositoryImpl
import com.haditorfi.minaz.data.db.AppDatabase
import com.haditorfi.minaz.data.personnel.Personnel
import com.haditorfi.minaz.data.personnel.PersonnelRepository
import com.haditorfi.minaz.data.personnel.PersonnelRepositoryImpl
import com.haditorfi.minaz.data.product.Product
import com.haditorfi.minaz.data.product.ProductRepository
import com.haditorfi.minaz.data.product.ProductRepositoryImpl
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.data.service.ServiceRepository
import com.haditorfi.minaz.data.service.ServiceRepositoryImpl
import com.haditorfi.minaz.feature.customer.CustomerViewModel
import com.haditorfi.minaz.feature.personnel.PersonnelViewModel
import com.haditorfi.minaz.feature.product.ProductViewModel
import com.haditorfi.minaz.feature.service.ServiceViewModel
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

        val dataClassModules = module {
            factory { Customer(0, "", "", "") }

            factory { Service(0, "", "") }

            factory { Product(0, "", "", "") }

            factory { Personnel(0, "", "", "", "") }

        }

        val repositoryModules = module {

            factory<CustomerRepository> { CustomerRepositoryImpl(get<AppDatabase>().customerDao()) }

            factory<ServiceRepository> { ServiceRepositoryImpl(get<AppDatabase>().serviceDao()) }

            factory<ProductRepository> { ProductRepositoryImpl(get<AppDatabase>().productDao()) }

            factory<PersonnelRepository> { PersonnelRepositoryImpl(get<AppDatabase>().personnelDao()) }
        }

        val viewModelModules = module {
            viewModel { CustomerViewModel(get()) }

            viewModel { ServiceViewModel(get()) }

            viewModel { ProductViewModel(get()) }

            viewModel { PersonnelViewModel(get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(listOf(appModules, dataClassModules, repositoryModules, viewModelModules))
        }
    }
}