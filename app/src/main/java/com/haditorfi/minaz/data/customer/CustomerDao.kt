package com.haditorfi.minaz.data.customer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customer")
    fun getAll(): List<Customer>

    @Insert
    fun insert(customers: Customer)

    @Delete
    fun delete(customer: Customer)
}