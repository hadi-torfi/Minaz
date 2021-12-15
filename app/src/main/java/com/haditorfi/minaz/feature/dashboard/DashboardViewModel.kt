package com.haditorfi.minaz.feature.dashboard

import androidx.lifecycle.ViewModel
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.data.product.Product
import com.haditorfi.minaz.data.service.provide.ProvideService

class DashboardViewModel() : ViewModel() {
    val customer: Customer = Customer()
    val product: Product = Product()
    val service: ProvideService = ProvideService()

}