package com.haditorfi.minaz.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.data.customer.CustomerDao

@Database(entities = [Customer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
}