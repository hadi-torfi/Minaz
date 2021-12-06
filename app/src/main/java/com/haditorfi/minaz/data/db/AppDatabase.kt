package com.haditorfi.minaz.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.data.customer.CustomerLocalDataSource
import com.haditorfi.minaz.data.personnel.Personnel
import com.haditorfi.minaz.data.personnel.PersonnelLocalDataSource
import com.haditorfi.minaz.data.product.Product
import com.haditorfi.minaz.data.product.ProductLocalDataSource
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.data.service.ServiceLocalDataSource
import com.haditorfi.minaz.data.service.provide.ProvideService
import com.haditorfi.minaz.data.service.provide.ProvideServiceLocalDataSource

@Database(
    entities = [Customer::class, Service::class, Product::class, Personnel::class, ProvideService::class],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerLocalDataSource
    abstract fun serviceDao(): ServiceLocalDataSource
    abstract fun provideServiceDao(): ProvideServiceLocalDataSource
    abstract fun productDao(): ProductLocalDataSource
    abstract fun personnelDao(): PersonnelLocalDataSource
}