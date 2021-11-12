package com.haditorfi.minaz.data.customer

interface CustomerDataSource {

    fun getAll(): List<Customer>

    fun insert(customers: Customer)

    fun delete(id: Long)

    fun update(customer: Customer)
}