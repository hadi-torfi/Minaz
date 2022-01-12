package com.haditorfi.minaz.data.customer

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerRepositoryImpl(private val customerDao: CustomerDao) :
    CustomerRepository {

    override val getAll: LiveData<List<Customer>> = customerDao.getAll()

    override fun insert(customer: Customer) {
        CoroutineScope(Dispatchers.IO).launch {
            customerDao.insert(customer)
        }
    }

    override fun delete(customer: Customer) {
        CoroutineScope(Dispatchers.IO).launch {
            customerDao.delete(customer)
        }
    }

    override fun update(customer: Customer) {
        CoroutineScope(Dispatchers.IO).launch {
            customerDao.update(customer)
        }
    }
}