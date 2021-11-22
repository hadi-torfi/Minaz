package com.haditorfi.minaz.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.data.customer.CustomerLocalDataSource
import com.haditorfi.minaz.data.personnel.Personnel
import com.haditorfi.minaz.data.personnel.PersonnelLocalDataSource
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.data.service.ServiceLocalDataSource

@Database(entities = [Customer::class, Service::class, Personnel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerLocalDataSource
    abstract fun serviceDao(): ServiceLocalDataSource
    abstract fun personnelDao() : PersonnelLocalDataSource
}