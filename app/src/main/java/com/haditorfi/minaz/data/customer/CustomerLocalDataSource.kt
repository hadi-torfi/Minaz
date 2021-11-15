package com.haditorfi.minaz.data.customer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CustomerLocalDataSource : CustomerDataSource {

    @Query("SELECT * FROM customer")
    override fun getAll(): LiveData<List<Customer>>

    @Insert
    override suspend fun insert(customers: Customer)

    @Query("DELETE FROM customer WHERE id = :id")
    override suspend fun delete(id: Long)

    @Update
    override suspend fun update(customer: Customer)
}