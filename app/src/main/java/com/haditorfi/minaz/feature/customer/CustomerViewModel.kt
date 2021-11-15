package com.haditorfi.minaz.feature.customer

import androidx.lifecycle.viewModelScope
import com.haditorfi.minaz.common.MyViewModel
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.data.customer.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerViewModel(private val customerRepository: CustomerRepository) : MyViewModel() {

    val customersLiveData = customerRepository.getAll

    fun insertCustomer(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            customerRepository.insert(customer)
        }
    }

    fun updateCustomer(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            customerRepository.update(customer)
        }
    }

    fun deleteCustomer(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            customerRepository.delete(id)
        }
    }
}