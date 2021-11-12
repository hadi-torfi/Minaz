package com.haditorfi.minaz.feature.customer

import androidx.lifecycle.MutableLiveData
import com.haditorfi.minaz.common.MyViewModel
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.data.customer.CustomerRepository

class CustomerViewModel(private val customerRepository: CustomerRepository) : MyViewModel() {
    val customersLiveData = MutableLiveData<List<Customer>>()

    init {
         /*val customer = Customer("هادی طرفی", "06142828513", "09352623050")
        insertCustomer(customer)*/
        getAllCustomer()
    }

    private fun getAllCustomer() {
        customersLiveData.value = customerRepository.getAll()
    }

    private fun insertCustomer(customer: Customer) {
        customerRepository.insert(customer)
    }

    private fun deleteCustomer(id: Long) {
        customerRepository.delete(id)
    }
}