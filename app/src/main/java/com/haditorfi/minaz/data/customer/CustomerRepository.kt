package com.haditorfi.minaz.data.customer

import androidx.lifecycle.LiveData

interface CustomerRepository {

    val getAll: LiveData<List<Customer>>

    suspend fun insert(customers: Customer)

    suspend fun delete(id: Long)

    suspend fun update(customer: Customer)
}