package com.haditorfi.minaz.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.data.customer.CustomerLocalDataSource
import com.haditorfi.minaz.data.product.Product
import com.haditorfi.minaz.data.product.ProductLocalDataSource
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.data.service.ServiceLocalDataSource
import com.haditorfi.minaz.data.service.provide.ProvideService
import com.haditorfi.minaz.data.service.provide.ProvideServiceDao
import com.haditorfi.minaz.data.staff.Staff
import com.haditorfi.minaz.data.staff.StaffDao

@Database(
    entities = [Customer::class, Service::class, Product::class, Staff::class, ProvideService::class],
    version = 1
)
@TypeConverters(ListConverter::class, DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerLocalDataSource
    abstract fun serviceDao(): ServiceLocalDataSource
    abstract fun provideServiceDao(): ProvideServiceDao
    abstract fun productDao(): ProductLocalDataSource
    abstract fun personnelDao(): StaffDao
}