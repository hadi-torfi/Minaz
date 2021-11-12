package com.haditorfi.minaz.data.customer

interface CustomerRepository {

    fun getAll(): List<Customer>

    fun insert(customers: Customer)

    fun delete(id: Long)

    fun update(customer: Customer)
}