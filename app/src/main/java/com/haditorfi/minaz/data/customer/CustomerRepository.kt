package com.haditorfi.minaz.data.customer

import androidx.lifecycle.LiveData

interface CustomerRepository {

    val getAll: LiveData<List<Customer>>

    fun insert(customer: Customer)

    fun delete(customer: Customer)

    fun update(customer: Customer)
}