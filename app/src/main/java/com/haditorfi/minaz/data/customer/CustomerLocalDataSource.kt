package com.haditorfi.minaz.data.customer

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CustomerLocalDataSource : CustomerDataSource {

    @Query("SELECT * FROM customer")
    override fun getAll(): LiveData<List<Customer>>

    @Insert
    override suspend fun insert(customer: Customer)

    @Delete
    override suspend fun delete(customer: Customer)

    @Update
    override suspend fun update(customer: Customer)
}