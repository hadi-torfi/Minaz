package com.haditorfi.minaz.data.customer

import androidx.lifecycle.LiveData

class CustomerRepositoryImpl(private val customerDao: CustomerDao) :
    CustomerRepository {

    override val getAll: LiveData<List<Customer>> = customerDao.getAll()

    override suspend fun insert(vararg customer: Customer) = customerDao.insert(*customer)

    override suspend fun delete(customer: Customer) = customerDao.delete(customer)

    override suspend fun update(customer: Customer) = customerDao.update(customer)
}