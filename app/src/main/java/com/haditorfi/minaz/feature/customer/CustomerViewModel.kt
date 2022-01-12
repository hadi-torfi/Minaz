package com.haditorfi.minaz.feature.customer

import androidx.lifecycle.ViewModel
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.data.customer.CustomerRepository

class CustomerViewModel(private val customerRepository: CustomerRepository) : ViewModel() {
    val customersLiveData = customerRepository.getAll

    fun insertCustomer(customer: Customer) = customerRepository.insert(customer)

    fun updateCustomer(customer: Customer) = customerRepository.update(customer)

    fun deleteCustomer(customer: Customer) = customerRepository.delete(customer)
}