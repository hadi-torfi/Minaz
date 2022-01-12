package com.haditorfi.minaz.data.customer

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customer")
    fun getAll(): LiveData<List<Customer>>

    @Insert
    suspend fun insert(customer: Customer)

    @Delete
    suspend fun delete(customer: Customer)

    @Update
    suspend fun update(customer: Customer)
}