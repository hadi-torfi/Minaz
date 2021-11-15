package com.haditorfi.minaz.data.customer

import androidx.lifecycle.LiveData

class CustomerRepositoryImpl(private val customerLocalDataSource: CustomerLocalDataSource) :
    CustomerRepository {

    override val getAll: LiveData<List<Customer>> = customerLocalDataSource.getAll()

    override suspend fun insert(customers: Customer) = customerLocalDataSource.insert(customers)

    override suspend fun delete(id: Long) = customerLocalDataSource.delete(id)

    override suspend fun update(customer: Customer) = customerLocalDataSource.update(customer)
}