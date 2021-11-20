package com.haditorfi.minaz.data.customer

import androidx.lifecycle.LiveData

class CustomerRepositoryImpl(private val customerLocalDataSource: CustomerLocalDataSource) :
    CustomerRepository {

    override val getAll: LiveData<List<Customer>> = customerLocalDataSource.getAll()

    override suspend fun insert(customer: Customer) = customerLocalDataSource.insert(customer)

    override suspend fun delete(customer: Customer) = customerLocalDataSource.delete(customer)

    override suspend fun update(customer: Customer) = customerLocalDataSource.update(customer)
}