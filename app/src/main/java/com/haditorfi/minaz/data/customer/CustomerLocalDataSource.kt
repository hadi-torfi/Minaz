package com.haditorfi.minaz.data.customer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CustomerLocalDataSource : CustomerDataSource {

    @Query("SELECT * FROM customer")
    override fun getAll(): List<Customer>

    @Insert
    override fun insert(customers: Customer)

    @Query("DELETE FROM customer WHERE id = :id")
    override fun delete(id: Long)

    @Update
    override fun update(customer: Customer)
}