package com.haditorfi.minaz.feature.dashboard

import androidx.lifecycle.MutableLiveData
import com.haditorfi.minaz.common.MyViewModel
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.data.customer.CustomerDao

class DashboardViewModel(customerDao: CustomerDao) : MyViewModel() {
    val customersLiveData = MutableLiveData<List<Customer>>()

    init {
        val cu2 = Customer( "hadi", "06142828513", "09166424196")
        customerDao.insert(cu2)
        customersLiveData.value = customerDao.getAll()
    }
}