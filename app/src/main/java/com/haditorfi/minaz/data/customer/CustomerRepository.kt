package com.haditorfi.minaz.data.customer

import androidx.lifecycle.LiveData

interface CustomerRepository {

    val getAll: LiveData<List<Customer>>

    suspend fun insert(vararg customer: Customer)

    suspend fun delete(customer: Customer)

    suspend fun update(customer: Customer)
}